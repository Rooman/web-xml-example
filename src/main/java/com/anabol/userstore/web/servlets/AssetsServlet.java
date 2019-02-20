package com.anabol.userstore.web.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;

public class AssetsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = req.getRequestURI();
        InputStream resourceAsStream = getClass().getClassLoader().getResourceAsStream(uri.substring(1));
        byte[] buffer = new byte[1024];
        int count;
        while ((count = resourceAsStream.read(buffer)) != -1) {
            resp.getOutputStream().write(buffer, 0, count);
        }
    }
}
