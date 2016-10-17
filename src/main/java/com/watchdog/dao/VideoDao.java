package com.watchdog.dao;

import com.watchdog.business.Video;
import java.util.List;

/**
 * Created by theNextThing on 10/12/2016.
 */

public interface VideoDao {

    //List<Video> listVideos();

    //Create
    void save(Video video);

    //Read
    Video getById(int id);

    //Update
    void update(Video video);

    //Delete
    void deleteById(int id);

    //Get All
    List<Video> getAll();
}

