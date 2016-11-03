package com.watchdog.dao.user;

import com.watchdog.business.User;

import java.util.List;

/**
 * Created by jmullen on 9/20/16.
 */
//CRUD operations
public interface UserDao {

    //Create
    void save(User user);

    //Read
    User getById(int id);

    User getByEmail(String email);

    boolean checkEmailExists(String email);

    //Update
    void update(User user);

    void updatePermission(User user, int id);

    //Delete
    void deleteById(int id);

    //Get All
    List<User> getAll();

}

