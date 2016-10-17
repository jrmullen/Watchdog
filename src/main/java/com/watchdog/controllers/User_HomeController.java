package com.watchdog.controllers;

import com.watchdog.business.Video;
import com.watchdog.dao.VideoDao;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;

@Controller
public class User_HomeController {

    @RequestMapping(value = "/user_home", method = RequestMethod.GET)
    public String listVideos(Video video, Model model){

        //Initialize database and create Dao object
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
        VideoDao videoDao = ctx.getBean("videoDaoImpl", VideoDao.class); //first parameter is the id found in the spring.xml file

        model.addAttribute("videoList", videoDao.getAll());

        return "user_home";
    }

}