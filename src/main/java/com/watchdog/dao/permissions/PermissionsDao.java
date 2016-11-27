package com.watchdog.dao.permissions;

import com.watchdog.business.Permissions;

import java.util.List;


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
