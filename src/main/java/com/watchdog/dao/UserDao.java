package com.watchdog.dao;

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

    User getByEmail(String id);

    //Update
    void update(User user);

    //Delete
    void deleteById(int id);

    //Get All
    List<User> getAll();
}

