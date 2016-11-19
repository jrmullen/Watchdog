package com.watchdog.dao.tag;

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
    void deleteByTagId(int id);

    boolean checkTagExists(String newTag);

    void deleteTagToVidByVidId(int id);

    void deleteTagToVidByTagId(int id);

    //Get All
    List<Tag> getAll();
}
