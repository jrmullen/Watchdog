package com.watchdog.controllers;

import com.watchdog.business.Device;
import com.watchdog.business.User;
import com.watchdog.business.Video;
import com.watchdog.dao.DeviceDao;
import com.watchdog.dao.UserDao;
import com.watchdog.dao.VideoDao;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;

@Controller
public class User_HomeController {

    @RequestMapping(value = "/user_home", method = RequestMethod.GET)
    public String listVideos(Device device, User user, Model model){

        //Initialize database and create Dao object
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
        DeviceDao deviceDao = ctx.getBean("deviceDaoImpl", DeviceDao.class); //first parameter is the id found in the spring.xml file
        UserDao userDao = ctx.getBean("userDaoImpl", UserDao.class);

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = auth.getName(); //get logged in username

        // String userId = userDao.getIdByEmail(userEmail);
        // model.addAttribute("user", userDao.getById(userId));
        model.addAttribute("deviceList", deviceDao.getAll());

        return "user_home";
    }

}