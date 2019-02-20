//package com.anabol.userstore;
//
//import com.anabol.userstore.dao.UserDao;
//import com.anabol.userstore.dao.jdbc.JdbcUserDao;
//import com.anabol.userstore.service.impl.DefaultUserService;
//import com.anabol.userstore.web.servlets.*;
//import org.eclipse.jetty.server.Server;
//import org.eclipse.jetty.servlet.ServletContextHandler;
//import org.eclipse.jetty.servlet.ServletHolder;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class Main {
//    public static void main(String[] args) throws Exception {
//        // configuration
//        // pass url to userDao
//        UserDao userDao = new JdbcUserDao();
//        DefaultUserService userService = new DefaultUserService();
//        userService.setUserDao(userDao);
//
//        List<String> tokens = new ArrayList<>();
//
//        //servlets
//        ShowUsersServlet showUsersServlet = new ShowUsersServlet();
//        showUsersServlet.setUserService(userService);
//
//        AddUserServlet addUserServlet = new AddUserServlet(tokens);
//        addUserServlet.setUserService(userService);
//
//        EditUserServlet editUserServlet = new EditUserServlet();
//        editUserServlet.setUserService(userService);
//
//        RemoveUserServlet removeUserServlet = new RemoveUserServlet();
//        removeUserServlet.setUserService(userService);
//
//
//        // servlets mapping
//        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
//        context.addServlet(new ServletHolder(showUsersServlet), "/users");
//        context.addServlet(new ServletHolder(addUserServlet), "/users/add");
//        context.addServlet(new ServletHolder(editUserServlet), "/users/*");
//        context.addServlet(new ServletHolder(removeUserServlet), "/users/remove");
//        context.addServlet(new ServletHolder(new AssetsServlet()), "/assets/*");
//        context.addServlet(new ServletHolder(new LoginServlet(tokens)), "/login");
//
//        Server server = new Server(8080);
//        server.setHandler(context);
//
//        server.start();
//    }
//}