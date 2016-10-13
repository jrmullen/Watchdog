package com.watchdog.business;

import java.sql.Date;
import java.sql.Time;
// import java.math.BigDecimal; Might be used later if we store video location as decimal instead of a String

public class Video {

    private int vid_id;
    private int user_id;
    private int device_id;
    private Time length;
    private Date date;
    private Time time;
    private String title;
    private String location; //BigDecimal location;
    private String description;
    private boolean isCompressed;
    private boolean isEncrypted;
    private long size;

    public void setVidID(int vid_id){
        this.vid_id = vid_id;
    }

    public int getVidID(){return vid_id; }

    public int getUserID() { return user_id; }

    public void setUserID(int user_id) { this.user_id = user_id; }

    public int getDeviceID() {return device_id; }

    public void setDeviceID(int device_id) { this.device_id = device_id; }

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

}
