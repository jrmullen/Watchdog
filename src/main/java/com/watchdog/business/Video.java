package com.watchdog.business;

import java.sql.Date;
import java.sql.Time;
// import java.math.BigDecimal; Might be used later if we store video location as decimal instead of a String

public class Video {

    private int vidId;
    private int userId;
    private int deviceId;
    private Time length;
    private Date date;
    private Time time;
    private String title;
    private String location;
    private String description;
    private boolean isCompressed;
    private boolean isEncrypted;
    private long size;

    public void setVidId(int vidId){
        this.vidId = vidId;
    }

    public int getVidId(){return vidId; }

    public int getUserId() { return userId; }

    public void setUserId(int userId) { this.userId= userId; }

    public int getDeviceId() {return deviceId; }

    public void setDeviceId(int deviceId) { this.deviceId = deviceId; }

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
