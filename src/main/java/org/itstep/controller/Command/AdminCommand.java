package org.itstep.controller.Command;

import javax.servlet.http.HttpServletRequest;

public class AdminCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        System.out.println("hello from AdminCommand");
        return "/WEB-INF/admin/adminbasis.jsp";
    }
}
