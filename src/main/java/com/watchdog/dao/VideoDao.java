package com.watchdog.dao;

import com.watchdog.business.User;
import com.watchdog.business.Video;

import java.util.List;

//CRUD operations
public interface VideoDao {

    //Create

    //Read
    Video getById(int id);

    Video getByDate(String date);

    //Update
    void update(Video video);

    //Delete
    void deleteById(int id);

    //Get All
    List<Video> getAll();
}

