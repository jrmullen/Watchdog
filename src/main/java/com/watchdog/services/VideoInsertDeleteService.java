package com.watchdog.services;


import com.watchdog.business.Video;
import com.watchdog.dao.device.DeviceDao;
import com.watchdog.dao.video.VideoDao;
import com.watchdog.dao.tag.TagDao;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.File;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * Created by renee on 11/15/2016.
 */
public class VideoInsertDeleteService {

    private static final int MAX_ALLOWED_AGE = 5;

    ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
    VideoDao videoDao = ctx.getBean("videoDaoImpl", VideoDao.class);
    TagDao tagDao = ctx.getBean("tagDaoImpl", TagDao.class);
    DeviceDao deviceDao = ctx.getBean("deviceDaoImpl", DeviceDao.class);

     public ArrayList<File> getFiles(File folder) {
         ArrayList<File> fileList = new ArrayList<File>();

         for (final File fileEntry : folder.listFiles()) {
             if (fileEntry.isFile()) {
                 fileList.add(fileEntry);
             } else if (fileEntry.isDirectory()) {
             }
         }
         return fileList;
     }


    public void insertVideoIntoDb(File file, File directory) {

        Video video = new Video();
        Time time = new Time(0);

        video.setFilePath(directory.getAbsolutePath());
        video.setLength(time);
        video.setIsCompressed(false);
        video.setIsEncrypted(false);
        video.setSize(file.length());
        video.setDate(parseDate(file.getName()));
        video.setTime(parseTime(file.getName()));
        video.setTitle(file.getName());
        video.setLocation(0.0);
        video.setDescription("Video description here");
        video.setDeviceMac(parseDeviceMac(file.getName()));
        System.out.println(video.getFilePath() + " " +
                video.getSize() + " " + video.getTitle() +
                                 " " +video.getDeviceMac());

        // LEAVE THIS COMMENTED OUT UNTIL SCRIPT FOR APPENDING MAC ADDRESSES TO END OF
        // VIDEO FILES NAMES IS FULLY FUNCTIONAL
        //if ((deviceDao.checkMacExists(video.getDeviceMac()))) {
            videoDao.save(video);
        //}
        /*else {
            System.out.println("No registered recording device with mac address of: " +
            video.getDeviceMac() + " in database. Video file " + video.getTitle() +
            " will now be deleted from directory.");
                    file.delete();
        }*/


    }


    public void deleteFileFromFolderAndDatabase(File file) {
        this.deleteVideoInfoFromDatabase(file.getName());
        file.delete();
    }


    public void deleteVideoInfoFromDatabase(String videoName) {
        Video video = videoDao.getByVideoTitle(videoName);

        if (tagDao.checkVidIdExistsInTagToVideo(video.getVideoId())) {
            tagDao.deleteTagToVidByVidId(video.getVideoId());
        }
        else {
            System.out.println("Video with id " + video.getVideoId() +
                    " is not tagged");
        }

        videoDao.deleteByName(videoName);
    }


    public boolean overMaxAllowedAge(File file) {

        int days = findDaysSinceFileCreated(file.getName());

        if (days >= MAX_ALLOWED_AGE) {
            return true;
        } else {
            return false;
        }
    }


    private int findDaysSinceFileCreated(String fileName) {

        String dateCreatedStr = parseDate(fileName);
        Date now = new Date();
        int days = 0;

        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

            Date dateCreated = dateFormat.parse(dateCreatedStr);
            String nowStr = dateFormat.format(now);
            Date currentDate = dateFormat.parse(nowStr);
            days = calculateDaysBetweenDates(dateCreated, currentDate);

            System.out.println("\nDate video created: " + dateCreated + "  Current Date: " +
                            currentDate + "\nDays between dates: " + days);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return days;
    }


    private static int calculateDaysBetweenDates(Date one, Date two) {
        long difference = (one.getTime()-two.getTime())/86400000;
        return (int)Math.abs(difference);
    }


    private String parseDate(String fileName) {

        String  fileYear = "",
                fileMonth = "",
                fileDay = "";
        int index = 1;

        while(index <=5) {
            if (fileName.indexOf('-', index) == index) {

                index += 5;
                fileYear = fileName.substring(index - 4, index);
                index += 2;
                fileMonth = fileName.substring(index - 2, index);
                index += 2;
                fileDay = fileName.substring(index - 2, index);
                String dateRecordedStr = fileYear + "-" +fileMonth + "-" + fileDay;
                return dateRecordedStr;
            }
            else {
                index += 1;
            }
        }
        return "0000-00-00";
    }


    private String parseDeviceMac(String fileName) {

        String fileMac = "";
        int index = 16;

        while(index <=20) {
            if (fileName.indexOf('_', index) == index)
            {
                index += 13;
                fileMac = fileName.substring(index - 12, index);

                fileMac = new StringBuilder(fileMac).insert(2, ":")
                        .insert(5, ":").insert(8, ":").insert(11, ":").insert(14, ":").toString();
                System.out.println("File mac: " + fileMac);

                return fileMac;
            } else {
                index += 1;
            }
        }
        System.out.println("Bad Mac or no Mac address on this file. Defaulting" +
                " to mac address of: 00:0a:95:9d:68:16");
        return "00:0a:95:9d:68:16";
    }


    private String parseTime(String fileName) {

        String  fileHour = "",
                fileMinute = "",
                fileSecond = "";

        int index = 1;
        while(index <=5) {
            if (fileName.indexOf('-', index) == index)
            {
                index += 11;
                fileHour = fileName.substring(index - 2, index);
                index += 2;
                fileMinute = fileName.substring(index - 2, index);
                index += 2;
                fileSecond = fileName.substring(index - 2, index);
                String timeRecordedStr = fileHour + ":" +fileMinute + ":" + fileSecond;
                return timeRecordedStr;
            }
            else {
                index += 1;
            }
        }
        return "00:00:00";
    }


    public boolean fileExistsInFolder(String fileName, File directory) {

        List<File> fileList = getFiles(directory);

        for (final File file : fileList) {
            if (file.getName().equals(fileName)) {
                return true;
            }
        }
        return false;
    }

    public boolean videoInfoExistsInDatabase(String name) {
        if(videoDao.checkVideoExists(name))
            return true;
        else
            return false;
    }


    public List getAllVideosInDatabase() {
        List videoList = videoDao.getAll();
        return videoList;
    }
}


