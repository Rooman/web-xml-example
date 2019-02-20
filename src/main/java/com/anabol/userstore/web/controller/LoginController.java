package com.anabol.userstore.web.controller;

import com.anabol.userstore.web.templater.PageGenerator;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Controller
public class LoginController {

    @RequestMapping(path = "/login", method = RequestMethod.GET)
    @ResponseBody
    public String loginPage() {
        Map<String, Object> pageVariables = new HashMap<>();
        return PageGenerator.instance().getPage("login.html", pageVariables);
    }

    @RequestMapping(path = "/login", method = RequestMethod.POST)
    protected String login(@RequestParam String login, @RequestParam String password) throws ServletException, IOException {
        System.out.println(login + "  " + password);
        String token = UUID.randomUUID().toString();
        return "redirect:/users";
    }
}
