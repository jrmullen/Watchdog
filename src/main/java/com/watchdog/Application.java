package com.watchdog;

import com.watchdog.dao.user.UserDao;
import com.watchdog.services.VideoInsertDeleteService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.File;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class Application {

    private static File directory = new File("c:/ftp/video");

    public static void main(String[] args) throws InterruptedException {
        SpringApplication.run(Application.class, args);

        //Get the Spring Context
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");

        //To use JdbcTemplate
        UserDao userDao = ctx.getBean("userDaoImpl", UserDao.class);

        // Run thread to check for add or delete video files and add the video info to the database
        ScheduledExecutorService exec = Executors.newSingleThreadScheduledExecutor();
        exec.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                System.out.println("\n\nRUNNING THREAD");
                if (directory.exists()) {

                    VideoInsertDeleteService videoInsertDeleteService = new VideoInsertDeleteService();
                    List<File> fileList = videoInsertDeleteService.getFiles(directory);

                    // delete file if its info exists in database but not in folder
                    // steps
                    // 1. check if exists in db
                    // 2. if exists in db, check if exists in folder
                    // 3. if exists in db and NOt in folder, delete video info from db
                    // 4. otherwise do nothing

                    //if(videoDao.getByVideoTitle())

                    for (final File file : fileList) {
                        if (videoInsertDeleteService.overMaxAllowedAge(file)) {
                            System.out.println("Delete file:  " + file.getName());

                            videoInsertDeleteService.deleteFile(file);

                        }
                        else if (videoInsertDeleteService.fileExists(file.getName(), directory) &&
                                !videoInsertDeleteService.videoInfoExistsInDatabase(file.getName())) {
                            videoInsertDeleteService.insertVideoIntoDb(file, directory);
                        }
                        else if (!videoInsertDeleteService.fileExists(file.getName(), directory) &&
                                videoInsertDeleteService.videoInfoExistsInDatabase(file.getName())) {
                            videoInsertDeleteService.insertVideoIntoDb(file, directory);
                        }
                    }
                }
                else {
                    System.out.println("Error! Unable to locate directory: " + directory.toString());
                }
            }
        }, 0, 60, TimeUnit.SECONDS);

        ctx.close();
    }

}
