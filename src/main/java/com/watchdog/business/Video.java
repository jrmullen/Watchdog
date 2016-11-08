package com.watchdog.business;

import java.sql.Date;
import java.sql.Time;
// import java.math.BigDecimal; Might be used later if we store video location as decimal instead of a String

public class Video {

    private int videoId;
    private int userId;
    private int deviceId;
    private String filepath;
    private Time length;
    private Date date;
    private Time time;
    private String title;
    private String location;
    private String description;
    private boolean isCompressed;
    private boolean isEncrypted;
    private long size;
    private String device_mac;

    public void setVideoId(int videoId){
        this.videoId = videoId;
    }

    public int getVideoId(){return videoId; }

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

    public boolean getIsCompressed() {
        return isCompressed;
    }

    public void setIsCompressed(boolean compressed) {
        isCompressed = compressed;
    }

    public boolean getIsEncrypted() {
        return isEncrypted;
    }

    public void setIsEncrypted(boolean encrypted) {
        isEncrypted = encrypted;
    }

    public void setSize(long size){
        this.size = size;
    }

    public long getSize(){
        return size;
    }

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    public String getDevice_mac() {
        return device_mac;
    }

    public void setDevice_mac(String device_mac) {
        this.device_mac = device_mac;
    }
}