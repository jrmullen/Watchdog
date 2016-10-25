package com.watchdog.dao;

import com.watchdog.business.Permissions;

import java.util.List;

/**
 * Created by BBuck on 10/4/16.
 */

// CRUD operations
public interface PermissionsDao {


    //Create
    void save (Permissions permissions);


    //Read
    Permissions getById (int id);


    //Update
    void update (Permissions permissions);

    //Delete
    void deleteById (int id);


    //Get all
    List<Permissions> getAll();
}
