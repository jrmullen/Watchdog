package com.watchdog.services;


import com.watchdog.business.Video;
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

    private static final int MAX_ALLOWED_AGE = 10;


    ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
    VideoDao videoDao = ctx.getBean("videoDaoImpl", VideoDao.class);
    TagDao tagDao = ctx.getBean("tagDaoImpl", TagDao.class);

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
        video.setDeviceMac("b8:27:eb:b3:10:82");
        System.out.println(video.getFilePath() +  " "  +
                video.getSize() + " " + video.getTitle());

        videoDao.save(video);

    }


    public void deleteFileFromFolderAndDatabase(File file) {
        Video video = videoDao.getByVideoTitle(file.getName());
        tagDao.deleteTagToVidByVidId(video.getVideoId());
        videoDao.deleteByName(file.getName());
        file.delete();
    }


    public void deleteVideoInfoFromDatabase(String videoName) {
        Video video = videoDao.getByVideoTitle(videoName);
        // create query and use here to check if video id exists in tag table
        tagDao.deleteTagToVidByVidId(video.getVideoId());
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


    public int findDaysSinceFileCreated(String fileName) {

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
        String fileYear = fileName.substring(3,7);
        String fileMonth = fileName.substring(7,9);
        String fileDay = fileName.substring(9,11);

        String dateRecordedStr = fileYear + "-" +fileMonth + "-" + fileDay;
        return dateRecordedStr;
    }


    private String parseTime(String fileName) {

        String fileHour = fileName.substring(11,13);
        String fileMinute = fileName.substring(13,15);
        String fileSecond = fileName.substring(15,17);

        String timeRecordedStr = fileHour + ":" +fileMinute + ":" + fileSecond;
        return timeRecordedStr;
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


