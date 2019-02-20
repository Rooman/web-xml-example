package com.anabol.userstore.dao.jdbc.mapper;

import com.anabol.userstore.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Mapper {
    public static User parse(ResultSet resultSet) throws SQLException {
        //User user = new User();
        User user = new User();
        user.setId(resultSet.getInt("id"));
        user.setFirstName(resultSet.getString("firstName"));
        user.setLastName(resultSet.getString("lastName"));
        user.setSalary(resultSet.getDouble("salary"));
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH);
        LocalDate dateOfBirth = LocalDate.parse(resultSet.getString("dateOfBirth"), dateTimeFormatter);
        user.setDateOfBirth(dateOfBirth);
        return user;

    }

    public static List<User> parseList(ResultSet resultSet) throws SQLException {
        List<User> users = new ArrayList<>();
        while (resultSet.next()) {
            User user = parse(resultSet);
            users.add(user);
        }
        return users;
    }
}
