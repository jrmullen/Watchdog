package com.watchdog.dao;

import com.watchdog.business.Tag;
import com.watchdog.business.Video;

import java.util.List;

public interface TagDao {

    //Create
    void save(Tag tag);

    //Read
    Tag getByTagId(int id);

    List<Tag> getByVidId(int id);

    //Update
    void update(Tag tag);

    //Delete
    void deleteById(int id);

    void deleteByVidId(int id);

    //Get All
    List<Tag> getAll();
}
