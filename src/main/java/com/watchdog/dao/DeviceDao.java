package com.watchdog.dao;

import com.watchdog.business.Device;

import java.util.List;

/**
 * Created by BBuck on 10/4/16.
 */

// CRUD operations
public interface DeviceDao {


    //Create
    public void save (Device device);


    //Read
    public Device getById (int id);


    //Update
    public void update (Device device);

    //Delete
    public void deleteById (int id);


    //Get all
    public List<Device> getall();
}
