package com.watchdog.services;


import com.watchdog.dao.device.DeviceDao;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.File;

/**
 * Created by renee on 11/15/2016.
 */
public class VideoInsertDeleteService {

    private static final int MAX_ALLOWED_AGE = 2;

    ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
    DeviceDao deviceDao = ctx.getBean("deviceDaoImpl", DeviceDao.class); //first parameter is the id found in the spring.xml file
/*
    public void getFileName(File folder) {
        for (final File fileEntry : folder.listFiles()) {
            if (fileEntry.isDirectory()) {
                getFileName(fileEntry);
            } else {
                System.out.println(fileEntry.getName());
            }
        }
    }*/

    public File[] getFiles(File folder) {
        File[] listOfFiles = folder.listFiles();

        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isFile()) {
                System.out.println("File: " + listOfFiles[i].getName());
            } else if (listOfFiles[i].isDirectory()) {
                System.out.println("Directory " + listOfFiles[i].getName());
            }
        }
        return listOfFiles;
    }

    public void insertVideoDB() {

    }

    public boolean overMaxAllowedAge(int days, File fileList) {

        //for (int i = 0; i < fileList.length; i++) {
            //calc time b/ date of vid and now
            //code here
            if (days >= MAX_ALLOWED_AGE) {
               // deleteFromFolder(fileList[i]);
                //deleteFromDatabase(fileList[i]);
                return true;
            } else {
                return false;
            }
        //}
    }

}


