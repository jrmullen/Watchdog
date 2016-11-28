package com.watchdog;

import com.watchdog.business.Video;
import com.watchdog.dao.tag.TagDao;
import com.watchdog.dao.user.UserDao;
import com.watchdog.dao.video.VideoDao;
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
        TagDao tagDao = ctx.getBean("tagDaoImpl", TagDao.class);


        // Run thread to check for add or delete video files and add the video info to the database
        ScheduledExecutorService exec = Executors.newSingleThreadScheduledExecutor();
        exec.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("\n\nRUNNING THREAD");
                    if (directory.exists()) {

                        VideoInsertDeleteService videoInsertDeleteService = new VideoInsertDeleteService();
                        List<File> fileList = videoInsertDeleteService.getFiles(directory);

                        // Iterate through files in directory
                        for (final File file : fileList) {

                            // Delete old file from database and directory
                            // if it is stored in the database and directory
                            if (videoInsertDeleteService.overMaxAllowedAge(file) &&
                                    videoInsertDeleteService.videoInfoExistsInDatabase(file.getName())) {
                                System.out.println("Delete file:  " + file.getName());

                                videoInsertDeleteService.deleteFileFromFolderAndDatabase(file);
                            }
                            // Delete old file from ONLY the directory if it is in the directory but NOT
                            // stored in the database
                            else if (videoInsertDeleteService.overMaxAllowedAge(file) &&
                                        !videoInsertDeleteService.videoInfoExistsInDatabase(file.getName())) {
                                System.out.println("Delete file from folder ONLY:  " + file.getName());

                                file.delete();
                            }
                            // Add new video file found in folder
                            else if (videoInsertDeleteService.fileExistsInFolder(file.getName(), directory) &&
                                    !videoInsertDeleteService.videoInfoExistsInDatabase(file.getName())) {
                                videoInsertDeleteService.insertVideoIntoDb(file, directory);
                            }
                        }

                        // Get rid of file info stored in database if someone manually deletes it from the directory
                        // meaning the file info is stored in the database, but the file is not in the directory.
                        List<Video> videoList = videoInsertDeleteService.getAllVideosInDatabase();

                        for (final Video video : videoList) {
                            if (videoInsertDeleteService.videoInfoExistsInDatabase(video.getTitle())) {

                                if (!videoInsertDeleteService.fileExistsInFolder(video.getTitle(), directory) &&
                                        videoInsertDeleteService.videoInfoExistsInDatabase(video.getTitle())) {

                                    System.out.println("Video will be deleted from database: " + video.getTitle());
                                    videoInsertDeleteService.deleteVideoInfoFromDatabase(video.getTitle());
                                }
                            }
                        }
                    } else {
                        System.out.println("Error! Unable to locate directory: " + directory.toString());
                    }
                }catch (Exception e) {
                    e.printStackTrace();

                }
            }
        }, 0, 70, TimeUnit.SECONDS);

        ctx.close();
    }

}
