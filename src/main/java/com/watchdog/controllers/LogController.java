package com.watchdog.controllers;

import com.watchdog.business.*;
import com.watchdog.business.Video;
import com.watchdog.dao.TagDao;
import com.watchdog.dao.VideoDao;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.watchdog.dao.DeviceDao;
import com.watchdog.dao.UserDao;


@Controller
public class LogController {

    @RequestMapping(value = "/logview", method = RequestMethod.GET)
    public String listLogs(Model model){

        //Initialize database and create Dao object
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
        VideoDao videoDao = ctx.getBean("videoDaoImpl", VideoDao.class); //first parameter is the id found in the spring.xml file
        TagDao tagDao = ctx.getBean("tagDaoImpl", TagDao.class);
        DeviceDao deviceDao = ctx.getBean("deviceDaoImpl", DeviceDao.class);

        List<Log> logList = new ArrayList<>();
        List<Video> videoList = videoDao.getAll();

        for (int i = 0; i < videoList.size(); i++) {
            Log log = new Log();
            Video video = videoList.get(i);
            List<Tag> tagList = tagDao.getByVidId(video.getVidId());

            String camera = deviceDao.getDeviceNameByVidId(video.getVidId());

            log.setRadioEntry(i);
            log.setDate(video.getDate());
            log.setStartTime(String.valueOf(video.getTime()));
            log.setLength(String.valueOf(video.getLength()));
            log.setCamera(camera);
            log.setTagList(tagList);
            log.setTags(log.getTagsString());
            logList.add(log);
        }

        //export list to model
        model.addAttribute("logList", logList);

        //redirect to logview page
        return "/logview";
    }


}
