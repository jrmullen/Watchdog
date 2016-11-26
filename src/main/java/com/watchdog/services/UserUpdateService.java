package com.watchdog.services;

import com.watchdog.business.User;
import com.watchdog.dao.user.UserDao;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Richard on 10/27/2016.
 */
public class UserUpdateService {
    public static void updateUser(User user) {
        //Initialize database and create UserDao object
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
        UserDao userDao = ctx.getBean("userDaoImpl", UserDao.class); //first parameter is the id found in the spring.xml file
        user.setEncodedPassword(user.getPassword());
        userDao.update(user);
    }
}
