package com.anabol.userstore.dao.jdbc;

import com.anabol.userstore.dao.UserDao;
import com.anabol.userstore.entity.User;
import com.anabol.userstore.dao.jdbc.mapper.Mapper;

import java.sql.*;
import java.util.List;

public class JdbcUserDao implements UserDao {
    private static final String JDBC_CONNECTION = "jdbc:sqlite:test.db";

    private static final String FIND_ALL_QUERY = "SELECT id, firstName, lastName, salary, dateOfBirth FROM user";
    private static final String FIND_BY_ID_QUERY = "SELECT id, firstName, lastName, salary, dateOfBirth FROM user WHERE id = ?";
    private static final String INSERT_QUERY = "INSERT INTO user (firstName, lastName, salary, dateOfBirth) VALUES (?, ?, ?, ?)";
    private static final String DELETE_BY_ID_QUERY = "DELETE FROM user WHERE id = ?";
    private static final String UPDATE_QUERY = "UPDATE user SET firstName = ?, lastName = ?, salary = ?, dateOfBirth = ? WHERE id = ?";

    @Override
    public List<User> findAll() {
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(FIND_ALL_QUERY)) {
            return Mapper.parseList(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("We got SQLException", e);
        }
    }

    @Override
    public User findById(int id) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID_QUERY)) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery();) {
                User user = Mapper.parse(resultSet);
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("We got SQLException", e);
        }
    }

    @Override
    public void insert(User user) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY)) {
            setStatementAttributes(preparedStatement, user);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("We got SQLException", e);
        }
    }

    @Override
    public void deleteById(int id) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_BY_ID_QUERY)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("We got SQLException", e);
        }
    }

    @Override
    public void update(User user) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_QUERY)) {
            setStatementAttributes(preparedStatement, user);
            preparedStatement.setInt(5, user.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("We got SQLException", e);
        }
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(JDBC_CONNECTION);
    }

    private void setStatementAttributes(PreparedStatement preparedStatement, User user) throws SQLException {
        preparedStatement.setString(1, user.getFirstName());
        preparedStatement.setString(2, user.getLastName());
        preparedStatement.setDouble(3, user.getSalary());
        preparedStatement.setString(4, user.getDateOfBirth().toString());
    }
}
