package com.watchdog.dao;

import com.watchdog.business.User;

import java.util.List;

/**
 * Created by jmullen on 9/20/16.
 */
//CRUD operations
public interface UserDao {

    //Create
    public void save(User user);

    //Read
    public User getById(int id);

    //Update
    public void update(User user);

    //Delete
    public void deleteById(int id);

    //Get All
    public List<User> getAll();
}

