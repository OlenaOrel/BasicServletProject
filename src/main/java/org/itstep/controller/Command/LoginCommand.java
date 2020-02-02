package org.itstep.controller.Command;

import org.itstep.model.entity.User;

import javax.servlet.http.HttpServletRequest;

public class LoginCommand implements Command{

    @Override
    public String execute(HttpServletRequest request) {
        String name = request.getParameter("name");
        String pass = request.getParameter("pass");

        if( name == null || name.equals("") || pass == null || pass.equals("")  ){
            //System.out.println("Not");
            return "/login.jsp";
        }
        System.out.println(name + " " + pass);
        //System.out.println("Yes!");
//todo: check login with DB

        if(CommandUtility.checkUserIsLogged(request, name)){
            return "/WEB-INF/error.jsp";
        }

        if (name.equals("Admin")){
            CommandUtility.setUserRole(request, User.ROLE.ADMIN, name);
            System.out.println("LoginCommand redirect to admin");
            return "redirect:/coffee/admin";
        } else if(name.equals("User")) {
            CommandUtility.setUserRole(request, User.ROLE.USER, name);
            return "redirect:/coffee/user";
        } else {
            CommandUtility.setUserRole(request, User.ROLE.UNKNOWN, name);
            System.out.println("LoginCommand unknown role");
            return "/login";
        }
    }

}
