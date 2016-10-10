package com.watchdog.dao;

/**
 * Created by jmullen on 9/20/16.
 */

import com.watchdog.business.User;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class UserDaoImpl implements UserDao {

    @Autowired
    private DataSource dataSource;
    
    private JdbcTemplate jdbcTemplate;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(this.dataSource);
    }

    @Override
    public void save(User user) {
        String query = "insert into user (USER_FNAME, USER_LNAME, USER_EMAIL, USER_PASSWORD) values (?,?,?,?)";

        Object[] args = new Object[]{user.getFirstName(), user.getLastName(), user.getEmail(), user.getEncodedPassword()};

        int out = jdbcTemplate.update(query, args);

        if (out != 0) {
            System.out.println("User saved");
        } else System.out.println("User save failed");
    }

    @Override
    public User getById(int id) {
        String query = "select USER_FNAME, USER_LNAME, USER_EMAIL from user where USER_ID = ?";

        //using RowMapper anonymous class, we can create a separate RowMapper for reuse
        User user = jdbcTemplate.queryForObject(query, new Object[]{id}, new RowMapper<User>() {

            @Override
            public User mapRow(ResultSet rs, int rowNum)
                    throws SQLException {
                User user = new User();
                user.setFirstName(rs.getString("USER_FNAME"));
                user.setLastName(rs.getString("USER_LNAME"));
                user.setEmail(rs.getString("USER_EMAIL"));
                return user;
            }
        });
        return user;
    }

    @Override
    public void update(User user) {
        String query = "update user set USER_FNAME = ?, USER_LNAME = ?, USER_EMAIL = ?, USER_PASSWORD = ? where USER_ID = ?";

        Object[] args = new Object[]{user.getFirstName(), user.getLastName(), user.getEmail(), user.getPassword(), user.getId()};

        int out = jdbcTemplate.update(query, args);
        if (out != 0) {
            System.out.println("User updated with id= " + user.getId());
        } else System.out.println("No User found with id= " + user.getId());
    }


    @Override
    public void deleteById(int id) {

        String query = "delete from user where USER_ID=?";

        int out = jdbcTemplate.update(query, id);
        if (out != 0) {
            System.out.println("User deleted with id= " + id);
        } else System.out.println("No User found with id= " + id);
    }

    @Override
    public List<User> getAll() {
        String query = "select USER_ID, USER_FNAME, USER_LNAME, USER_EMAIL from user";

        List<User> userList = new ArrayList<User>();

        List<Map<String, Object>> userRows = jdbcTemplate.queryForList(query);

        for (Map<String, Object> userRow : userRows) {
            User user = new User();
            user.setId(Integer.parseInt(String.valueOf(userRow.get("USER_ID"))));
            user.setFirstName(String.valueOf(userRow.get("USER_FNAME")));
            user.setLastName(String.valueOf(userRow.get("USER_LNAME")));
            user.setEmail(String.valueOf(userRow.get("USER_EMAIL")));
            userList.add(user);
        }
        return userList;
    }

    @Override
    public String getPasswordByEmail(String email) {
        String query = "select USER_PASSWORD from user where USER_EMAIL = ?";
        return jdbcTemplate.queryForObject(query, String.class, email);
    }

}

