package com.watchdog.business;

public class Video {

    private int id;
    private String length;
    private String date;
    private String time;
    private String title = "";
    private String location;
    private String description;
    private boolean isCompressed = false;
    private boolean isEncrypted = false;
    private long size = 0;

    public void setID(int id){
        this.id = id;
    }

    public int getID(){
        return id;
    }

    public void setLength(String length){
        this.length = length;
    }

    public String getLength(){
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
