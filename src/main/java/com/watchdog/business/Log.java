package com.watchdog.business;

import java.util.Date;
import java.util.List;

public class Log {
    private int id;
    public int vid_id;
    private Date date;
    private String startTime;
    private String length;
    private String camera;
    private List<Tag> tagList;
    private String tags;


    //pull all tags for log and construct into comma separated string
    public String getTagsString(){
        List<Tag> alpha = this.tagList;
        String beta;

        //The string builder used to construct the string
        StringBuilder commaSepValueBuilder = new StringBuilder();
        //Looping through the list
        for ( int i = 0; i < alpha.size(); i++){
            Tag j = alpha.get(i);
            //append the value into the builder
            commaSepValueBuilder.append(j.getTag_name());

            //if the value is not the last element of the list
            //then append the comma(,) as well
            if ( i != alpha.size()-1){
                commaSepValueBuilder.append(", ");
            }
        }

        beta = commaSepValueBuilder.toString();

        return beta;
    }

    public int getVidId() { return vid_id;}

    public void setVidId(int id) { this.vid_id = id;}

    public int getId() { return id;}

    public void setId(int id) { this.id = id;}

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

    public List<Tag> getTagList() {
        return tagList;
    }

    public void setTagList(List<Tag> tagList) {
        this.tagList = tagList;
    }

    public String getTags() { return tags;}

    public void setTags(String tags) { this.tags = tags;}
}
