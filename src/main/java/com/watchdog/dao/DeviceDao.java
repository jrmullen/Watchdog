package com.watchdog.dao;

import com.watchdog.business.Device;

import java.util.List;

/**
 * Created by BBuck on 10/4/16.
 */

// CRUD operations
public interface DeviceDao {


    //Create
    void save (Device device);


    //Read
    Device getById (int id);


    //Update
    void update (Device device);

    //Delete
    void deleteById (int id);


    //Get all
    List<Device> getAll();
}