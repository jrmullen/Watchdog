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
        UserDao userDao = ctx.getBean("userDaoImpl", UserDao.class); //first parameter is the id found in the spring.xml file
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
                                !videoDao.checkVideoExists(file.getName())) {
                            videoInsertDeleteService.insertVideoIntoDb(file, directory);
                        }
                    }
                }
                else {
                    System.out.println("Error! Unable to locate directory: " + directory.toString());
                }

            }
        }, 0, 60, TimeUnit.SECONDS);

        /*// start test video query code here

        // test insert video into database
        Video video = new Video();
        video.setFilePath("test file path");
        Time time = new Time(1000);
        video.setLength(time);
        video.setIsCompressed(false);
        video.setIsEncrypted(false);
        video.setSize(100);
        Date date = new Date(11/10/2016);
        video.setDate(date);
        video.setTime(time);
        video.setTitle("file name");
        video.setLocation(22.0);
        video.setDescription("a description");
        video.setDeviceMac("87:67:45:67:89:ab");

        videoDao.save(video);

        // test get video by title
        // works if there is video in database with the title: A trespassing squirrel
        Video video2 = videoDao.getByVideoTitle("A trespassing squirrel");
        System.out.println("Video get by title test: " + video2.getVideoId() + " " + video2.getFilePath() +
                " " + video2.getLength() + " "  + video2.getIsCompressed() + " " + video2.getIsEncrypted() +
                " " + video2.getSize() + " " + video2.getDate()  + " " + video2.getTime()  + " " +
                video2.getTitle()  + " " + video2.getLocation()  + " " + video2.getDescription() + " " +
                video2.getDeviceMac());

        // test get vide by id
        Video video1 = videoDao.getByVidId(1);
        System.out.println("Video get by id test: " + video1.getFilePath() + " " + video1.getLength() + " "  +
                video1.getIsCompressed() + " " + video1.getIsEncrypted() + " " + video1.getSize()
                + " " + video1.getDate()  + " " + video1.getTime()  + " " + video1.getTitle()  +
                " " + video1.getLocation()  + " " + video1.getDescription() + " " +
                video1.getDeviceMac());

        // test delete video by id
        // This works if there is a video with an id of 3 in database
        videoDao.deleteByVidId(3);

        // test update video
        video1.setFilePath("update path");
        video1.setLength(time);
        video1.setIsCompressed(false);
        video1.setIsEncrypted(false);
        video1.setSize(999);
        video1.setDate(date);
        video1.setTime(time);
        video1.setTitle(" update file name");
        video1.setLocation(111.0);
        video1.setDescription("updated description");
        video1.setDeviceMac("87:67:45:67:89:ab");
        videoDao.update(video1, 1);

        // test delete by name
        // works if there is a video in the database with title of Trespasser
        videoDao.deleteByName("Trespasser");

        // test get all videos in database
        List<Video> videoTestList = videoDao.getAll();
        System.out.println("Get all videos test:");
        for (Video v : videoTestList) {
            System.out.println(v.getVideoId() + " " + v.getFilePath() + " " + v.getLength() + " "  +
                    v.getIsCompressed() + " " + v.getIsEncrypted() + " " + v.getSize()
                    + " " + v.getDate()  + " " + v.getTime()  + " " + v.getTitle()  +
                    " " + v.getLocation()  + " " + v.getDescription() + " " +
                    v.getDeviceMac());
        }*/

        // end test video query code here

        ctx.close();
    }

}
