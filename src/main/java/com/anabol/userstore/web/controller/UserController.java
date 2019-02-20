package com.anabol.userstore.web.controller;

import com.anabol.userstore.entity.User;
import com.anabol.userstore.service.UserService;
import com.anabol.userstore.web.templater.PageGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
public class UserController {

    @Autowired
    @Qualifier("userServiceBean")
    private UserService userService;

    @RequestMapping(method = RequestMethod.GET, path = "/users")
    public void getUsers(HttpServletResponse httpServletResponse) throws IOException {
        Map<String, Object> pageVariables = new HashMap<>();
        pageVariables.put("users", userService.findAll());
        httpServletResponse.getWriter().println(PageGenerator.instance().getPage("users.html", pageVariables));
        httpServletResponse.setContentType("text/html;charset=utf-8");
        httpServletResponse.setStatus(HttpServletResponse.SC_OK);
    }


    @RequestMapping(method = RequestMethod.GET, path = "/users/{id}")
    public void getUserById(@PathVariable int id, HttpServletResponse httpServletResponse) throws IOException {
        User byId = userService.findById(id);
        httpServletResponse.getWriter().println(byId);
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
