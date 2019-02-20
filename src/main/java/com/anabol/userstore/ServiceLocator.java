package com.anabol.userstore;

import com.anabol.userstore.dao.UserDao;
import com.anabol.userstore.dao.jdbc.JdbcUserDao;
import com.anabol.userstore.service.UserService;
import com.anabol.userstore.service.impl.DefaultUserService;

import java.util.HashMap;
import java.util.Map;

public class ServiceLocator {
    private static final Map<Class<?>, Object> SERVICES = new HashMap<>();

    static {
        UserDao userDao = new JdbcUserDao();
        DefaultUserService userService = new DefaultUserService();
        userService.setUserDao(userDao);
        SERVICES.put(UserService.class, userService);
    }

    public static <T> T getService(Class<T> clazz) {
        return clazz.cast(SERVICES.get(clazz));
    }
}
