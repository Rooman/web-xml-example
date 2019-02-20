package com.anabol.userstore.web.servlets;

import com.anabol.userstore.ServiceLocator;
import com.anabol.userstore.service.UserService;
import com.anabol.userstore.web.templater.PageGenerator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ShowUsersServlet extends HttpServlet {
    private UserService userService = ServiceLocator.getService(UserService.class);

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException, IOException {
        Map<String, Object> pageVariables = new HashMap<>();
        pageVariables.put("users", userService.findAll());
        response.getWriter().println(PageGenerator.instance().getPage("users.html", pageVariables));
        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
