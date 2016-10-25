package com.watchdog.business;

import java.util.Date;

public class Log {
    private int radioEntry;
    private Date date;
    private String startTime;
    private String length;
    private String camera;
    private String[] tagArray;


    //pull all tags for log and construct into string
    public String getTags(){
        String tagList = "null";

        return tagList;
    }

    public int getRadioEntry() {
        return radioEntry;
    }

    public void setRadioEntry(int radioEntry) {
        this.radioEntry = radioEntry;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getCamera() {
        return camera;
    }

    public void setCamera(String camera) {
        this.camera = camera;
    }

    public String[] getTagArray() {
        return tagArray;
    }

    public void setTagArray(String[] tagArray) {
        this.tagArray = tagArray;
    }
}
