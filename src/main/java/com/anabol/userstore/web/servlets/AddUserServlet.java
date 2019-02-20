package com.anabol.userstore.web.servlets;

import com.anabol.userstore.entity.User;
import com.anabol.userstore.service.UserService;
import com.anabol.userstore.web.templater.PageGenerator;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public class AddUserServlet extends HttpServlet {
    private UserService userService;
    List<String> tokens;

    public AddUserServlet(List<String> tokens) {
        this.tokens = tokens;
    }

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException, IOException {


        boolean isAuth = false;
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("user-token")) {
                    if (tokens.contains(cookie.getValue())) {
                        isAuth = true;
                    }
                }
            }
        }

        if (!isAuth) {
            response.sendRedirect("/login");
        } else {
            String page = PageGenerator.instance().getPage("add.html", null);
            response.getWriter().println(page); // if size => 5mb  // 3 (8192) // 200 OK
            response.setContentType("text/html;charset=utf-8"); // 2
            response.setStatus(HttpServletResponse.SC_OK); // 1
        }


    }

    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException, IOException {
        User user = new User();
        user.setFirstName(request.getParameter("firstName"));
        user.setLastName(request.getParameter("lastName"));
        user.setSalary(Double.valueOf(request.getParameter("salary")));
        user.setDateOfBirth(LocalDate.parse(request.getParameter("dateOfBirth")));
        userService.insert(user);

        response.sendRedirect("/users");
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

}
