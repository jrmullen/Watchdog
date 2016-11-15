package com.watchdog.services;


import com.watchdog.dao.device.DeviceDao;
import com.watchdog.dao.video.VideoDao;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.File;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Created by renee on 11/15/2016.
 */
public class VideoInsertDeleteService {

    private static final int MAX_ALLOWED_AGE = 2;

    ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
    VideoDao videoeDao = ctx.getBean("videoDaoImpl", VideoDao.class);

     public ArrayList<File> getFiles(File folder) {
         ArrayList<File> fileList = new ArrayList<File>();

         for (final File fileEntry : folder.listFiles()) {
             if (fileEntry.isFile()) {
                 System.out.println("File:  " + fileEntry.getName());
                 fileList.add(fileEntry);
             } else if (fileEntry.isDirectory()) {
                 System.out.println("Directory:  " + fileEntry.getName());
             }
         }
         return fileList;
     }

    public void insertVideoIntoDb() {

    }

    public void deleteFile(File file) {
       //deleteVideoFromDb(file.getName());
    }

    public void deleteVideoFromDb(String fileName) {

    }

    public boolean overMaxAllowedAge(File file) {

        int days = parseDays(file.getName());

        if (days >= MAX_ALLOWED_AGE) {
            return true;
        } else {
            return false;
        }
    }

    public int parseDays(String fileName) {

        String fileYear = fileName.substring(3,7);
        String fileMonth = fileName.substring(7,9);
        String fileDay = fileName.substring(9,11);

        String dateCreatedStr = fileMonth + "-" + fileDay  + "-" + fileYear;
        Date now = new Date();

        int days = 0;


        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");

            Date dateCreated = dateFormat.parse(dateCreatedStr);
            String nowStr = dateFormat.format(now);
            Date currentDate = dateFormat.parse(nowStr);
            days = daysBetween(dateCreated, currentDate);

            System.out.println("\nDate video created: " + dateCreated + "  Current Date: " +
                            currentDate + "\nDays between dates: " + days);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return days;
    }

    private static int daysBetween(Date one, Date two) {
        long difference = (one.getTime()-two.getTime())/86400000;
        return (int)Math.abs(difference);
    }
}


