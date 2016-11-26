package com.watchdog.dao.tag;

import com.watchdog.business.Tag;

import java.util.List;

public interface TagDao {

    //Create
    void save(Tag tag);

    //Read
    Tag getById(int id);

    //Update
    void update(Tag tag);

    //Delete
    void deleteById(int id);

    //Get All
    List<Tag> getAll();
}
