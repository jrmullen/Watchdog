package com.watchdog.controllers;

import com.watchdog.business.Video;
import com.watchdog.dao.VideoDao;
import com.watchdog.dao.VideoDaoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
public class User_HomeController {


   // @RequestMapping("/user_home")
    //public void userHome(){
    //}

    //@GetMapping(value = "/user_home")
    //public String user_home(Video video) {
       // return "user_home";
    //}

    //@RequestMapping(value = "/user_home", method = RequestMethod.GET)
    //public String index(Principal principal) {
     //   return principal != null ? "user_home" : "user_home";
    //}

    //@PostMapping(value = "/user_home")
    @RequestMapping(value = "/user_home", method = RequestMethod.GET)
    public String listVideos(Video video, Model model){

        //Initialize database and create DeviceDao object
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
        VideoDao videoDao = ctx.getBean("videoDaoImpl", VideoDao.class); //first parameter is the id found in the spring.xml file
        video = new Video();
        video = videoDao.getById(2);

        model.addAttribute("title", video.getTitle());

        return "user_home";
    }

}