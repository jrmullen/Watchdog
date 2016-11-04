package com.watchdog;

import com.watchdog.business.Device;
import com.watchdog.dao.user.UserDao;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@SpringBootApplication
public class Application {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class, args);

        //Get the Spring Context
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");

        //To use JdbcTemplate
        UserDao userDao = ctx.getBean("userDaoImpl", UserDao.class); //first parameter is the id found in the spring.xml file
        
        ctx.close();

        //TODO: remove this test code
        Device device = new Device();
        device.buildDeviceUrl("192.168.12.4.1", 8081);
        System.out.println(device.getDeviceUrl());

    }
}
