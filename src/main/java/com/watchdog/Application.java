package com.watchdog;

import com.watchdog.business.Device;
import com.watchdog.dao.user.UserDao;
import com.watchdog.services.VideoInsertDeleteService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.File;

@SpringBootApplication
public class Application {


    private static File directory = new File("c:/ftp/video");

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class, args);

        //Get the Spring Context
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");

        //To use JdbcTemplate
        UserDao userDao = ctx.getBean("userDaoImpl", UserDao.class); //first parameter is the id found in the spring.xml file
        VideoInsertDeleteService videoInsertDeleteService = new VideoInsertDeleteService();
        videoInsertDeleteService.getFiles(directory);

        ctx.close();
    }





}
