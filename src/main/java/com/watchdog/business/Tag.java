package com.watchdog.business;

import java.util.List;

public class Tag {
    private int tagId;
    private String tagName;
    private List<Video> vidList;

    public List<Video> getVidList() {
        return vidList;
    }

    public void setVidList(List<Video> vidList) {
        this.vidList = vidList;
    }

    public int getTagId() {
        return tagId;
    }

    public void setTagId(int tagId) {
        this.tagId = tagId;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }
}
