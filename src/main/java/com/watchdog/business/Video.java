package com.watchdog.business;

import java.sql.Date;
import java.sql.Time;
// import java.math.BigDecimal; Might be used later if we store video location as decimal instead of a String

public class Video {

    private int videoId;
    private int userId;
    private String filePath;
    private Time length;
    private Date date;
    private Time time;
    private String title;
    private String location;
    private String description;
    private boolean isCompressed;
    private boolean isEncrypted;
    private long size;
    private String deviceMac;
    private String device_mac;

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

    public void setDate(Date date){
        this.date = date;
    }

    public Date getDate(){
        return date;
    }

    public void setTime(Time time){
        this.time = time;
    }

    public Time getTime(){
        return time;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public String getTitle(){
        return title;
    }

    public void setLocation(String location){
        this.location = location;
    }

    public String getLocation(){
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