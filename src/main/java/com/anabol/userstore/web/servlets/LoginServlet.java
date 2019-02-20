package com.anabol.userstore.web.servlets;

import com.anabol.userstore.web.templater.PageGenerator;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class LoginServlet extends HttpServlet {
    List<String> tokens;

    public LoginServlet(List<String> tokens) {
        this.tokens = tokens;
    }

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException, IOException {
        Map<String, Object> pageVariables = new HashMap<>();
        response.getWriter().println(PageGenerator.instance().getPage("login.html", pageVariables));
        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        System.out.println(login + "  " + password);
        String token = UUID.randomUUID().toString();
        tokens.add(token);
        Cookie cookie = new Cookie("user-token", token);
        resp.addCookie(cookie);
        resp.sendRedirect("/users");
    }
}
