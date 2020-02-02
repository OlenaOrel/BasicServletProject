package org.itstep.controller.Command;

import org.itstep.model.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LogOutCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        String userName = (String) request.getSession().getServletContext().getAttribute("userName");
        System.out.println("LogOut username: " + userName);
        CommandUtility.logOutUser(request, userName);
        CommandUtility.setUserRole(request, User.ROLE.UNKNOWN, "Guest");
        return "/index.jsp";
    }
}
