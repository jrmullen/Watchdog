package com.watchdog.dao;

import com.watchdog.business.User;
import com.watchdog.business.Video;

import java.util.List;

//CRUD operations
public interface VideoDao {

    //Create
    void save(Video video);

    //Read
    Video getByID(int id);

    //Update
    void update(Video video);

    //Delete
    void deleteByID(int id);

    //Get All
    List<Video> getAll();
}

