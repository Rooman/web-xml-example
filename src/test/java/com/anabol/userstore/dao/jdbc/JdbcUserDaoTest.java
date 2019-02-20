package com.anabol.userstore.dao.jdbc;

import com.anabol.userstore.entity.User;
import org.junit.Test;
import java.util.List;

import static org.junit.Assert.*;

public class JdbcUserDaoTest {

    @Test
    public void testFindAll() {
        JdbcUserDao jdbcUserDao = new JdbcUserDao();
        List<User> list = jdbcUserDao.findAll();

        assertFalse(list.isEmpty());
        for (User user : list) {
            assertNotSame(user.getId(), 0);
            assertNotNull(user.getFirstName());
            assertNotNull(user.getLastName());
            assertTrue(user.getSalary() >= 0);
            assertNotNull(user.getDateOfBirth());
        }
    }

    @Test
    public void testFindById() {
        JdbcUserDao jdbcUserDao = new JdbcUserDao();
        assertNull(jdbcUserDao.findById(101));
    }
}
