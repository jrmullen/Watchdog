package com.watchdog;

import com.watchdog.business.Video;
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

        VideoDao videoDao = ctx.getBean("videoDaoImpl", VideoDao.class);

        // Run thread to check for add or delete video files and add the video info to the database
        ScheduledExecutorService exec = Executors.newSingleThreadScheduledExecutor();
        exec.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                System.out.println("\n\nRUNNING THREAD");
                if (directory.exists()) {

                    VideoInsertDeleteService videoInsertDeleteService = new VideoInsertDeleteService();
                    List<File> fileList = videoInsertDeleteService.getFiles(directory);

                    for (final File file : fileList) {
                        if (videoInsertDeleteService.overMaxAllowedAge(file)) {
                            System.out.println("Delete file:  " + file.getName());

                            videoInsertDeleteService.deleteFileFromFolderAndDatabase(file);

                        } else if (videoInsertDeleteService.fileExistsInFolder(file.getName(), directory) &&
                                !videoInsertDeleteService.videoInfoExistsInDatabase(file.getName())) {
                            videoInsertDeleteService.insertVideoIntoDb(file, directory);
                        }
                    }

                   /* List<Video> videoList = videoInsertDeleteService.getAllVideosInDatabase();

                    for (final Video video : videoList) {
                        System.out.println("Inside videList loop with video: " + video.getTitle());
                        if (videoInsertDeleteService.videoInfoExistsInDatabase(video.getTitle())) {
                            System.out.println("Returned true that video exists in db: " + video.getTitle());

                            for (final File file: fileList) {
                                System.out.println("Inside fileList with file: " + file.getName());
                                if(!videoInsertDeleteService.fileExistsInFolder(video.getTitle(), directory)) {
                                    System.out.println("Video will be deleted from database: " + video.getTitle());
                                    videoInsertDeleteService.deleteVideoInfoFromDatabase(video.getTitle());


                                }
                                else
                                    System.out.println("Video NOT deleted database: " + video.getTitle());
                            }
                        }
                    }*/
                }
                else {
                    System.out.println("Error! Unable to locate directory: " + directory.toString());
                }
            }
        }, 0, 60, TimeUnit.SECONDS);

        ctx.close();
    }

}
