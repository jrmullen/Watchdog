package com.watchdog.controllers;

import com.watchdog.business.*;
import com.watchdog.business.Video;
import com.watchdog.dao.TagDao;
import com.watchdog.dao.VideoDao;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.watchdog.dao.DeviceDao;
import com.watchdog.dao.UserDao;


@Controller
@RequestMapping(value = "/logview", method = { RequestMethod.GET, RequestMethod.POST })
public class LogController {

    List<Log> logList = new ArrayList<>();
    List<Log> previousList = new ArrayList<>();
    List<Log> blankList = new ArrayList<>();
    Model blankModel;

    @RequestMapping
    public String listLogs(@Valid Model model){

        //Initialize database and create Dao object
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
        VideoDao videoDao = ctx.getBean("videoDaoImpl", VideoDao.class); //first parameter is the id found in the spring.xml file
        TagDao tagDao = ctx.getBean("tagDaoImpl", TagDao.class);
        DeviceDao deviceDao = ctx.getBean("deviceDaoImpl", DeviceDao.class);

        List<Video> videoList = videoDao.getAll();

        for (int i = 0; i < videoList.size(); i++) {
            Log log = new Log();
            Video video = videoList.get(i);
            List<Tag> tagList = tagDao.getByVidId(video.getVidId());

            String camera = deviceDao.getDeviceNameByVidId(video.getVidId());

            log.setId(i);
            log.setVidId(video.getVidId());
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

    //delete device
    @RequestMapping(params = "deleteLog")
    public String delete(@RequestParam int vid_id, int id, Log log, Model model) {

        //Initialize database and create videoDao, tagDao object
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
        VideoDao videoDao = ctx.getBean("videoDaoImpl", VideoDao.class);
        TagDao tagDao = ctx.getBean("tagDaoImpl", TagDao.class);

        tagDao.deleteByVidId(vid_id);
        videoDao.deleteById(vid_id);

        if(logList.size() == 1) {
            logList.remove(id);
            model.addAttribute("logList", logList);
        }
        
        return "/logview";
    }


}
