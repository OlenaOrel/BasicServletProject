package org.itstep.controller.Command;

import org.itstep.model.entity.User;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashSet;
import java.util.Set;

class CommandUtility {
    static void setUserRole(HttpServletRequest request,
                     User.ROLE role, String name) {
        HttpSession session = request.getSession();
        ServletContext context = request.getServletContext();
        context.setAttribute("userName", name);
        session.setAttribute("role", role);
    }

    static boolean checkUserIsLogged(HttpServletRequest request, String userName){
       Set<String> loggedUsers = getLoggedUsersFromContext(request);

        if(loggedUsers.stream().anyMatch(userName::equals)){
            return true;
        }
        loggedUsers.add(userName);
        setLoggedUsersToContext(request, loggedUsers);
        return false;
    }
    static void logOutUser(HttpServletRequest request, String userName) {
        HttpSession session = request.getSession();

        Set<String> loggedUsers = getLoggedUsersFromContext(request);
        loggedUsers.remove(userName);
        request.getServletContext().removeAttribute("userName");
        System.out.println("LoggedUser after remove: " + loggedUsers);
        setLoggedUsersToContext(request, loggedUsers);
        session.removeAttribute("userName");
        session.invalidate();
    }

    private static Set<String> getLoggedUsersFromContext(HttpServletRequest request) {
        return (HashSet<String>) request.getSession().getServletContext().getAttribute("loggedUsers");
    }

    private static void setLoggedUsersToContext(HttpServletRequest request, Set<String> loggedUsers) {
        request.getSession().getServletContext().setAttribute("loggedUsers", loggedUsers);
    }
}
