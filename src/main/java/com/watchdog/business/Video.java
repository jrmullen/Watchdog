package com.watchdog.business;

import java.util.Date;
import java.sql.Time;

public class Video {

    private int videoId;
    private int userId;
    private String filePath;
    private Time length;
    private String date;
    private String time;
    private String title;
    private double location;
    private String description;
    private boolean isCompressed;
    private boolean isEncrypted;
    private long size;
    private String deviceMac;

    public void setVideoId(int videoId){
        this.videoId = videoId;
    }

    public int getVideoId(){return videoId; }

    public int getUserId() { return userId; }

    public void setUserId(int userId) { this.userId= userId; }

    public void setLength(Time length){
        this.length = length;
    }

    public Time getLength(){
        return length;
    }

    public void setDate(String date){
        this.date = date;
    }

    public String getDate(){
        return date;
    }

    public void setTime(String time){
        this.time = time;
    }

    public String getTime(){
        return time;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public String getTitle(){
        return title;
    }

    public void setLocation(Double location){
        this.location = location;
    }

    public Double getLocation(){
        return location;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public String getDescription(){
        return description;
    }

    public void setIsCompressed(boolean isCompressed){
        this.isCompressed = isCompressed;
    }

    public boolean getIsCompressed(){
        return isCompressed;
    }

    public void setIsEncrypted(boolean isEncrypted){
        this.isEncrypted = isEncrypted;
    }

    public boolean getIsEncrypted(){
        return isEncrypted;
    }

    public void setSize(long size){
        this.size = size;
    }

    public long getSize(){
        return size;
    }

    public String getFilePath() { return filePath; }

    public void setFilePath(String filePath) { this.filePath = filePath; }

    public String getDeviceMac() { return deviceMac; }

    public void setDeviceMac(String deviceMac) { this.deviceMac = deviceMac; }
}