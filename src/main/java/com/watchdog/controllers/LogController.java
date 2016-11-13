package com.watchdog.controllers;

import com.watchdog.business.*;
import com.watchdog.business.Video;
import com.watchdog.dao.video.*;
import com.watchdog.dao.tag.*;
import com.watchdog.dao.device.*;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Controller
@RequestMapping(value = "/logview", method = { RequestMethod.GET, RequestMethod.POST })
public class LogController {

    List<Log> logList = new ArrayList<>();
    List<Tag> tagList = new ArrayList<>();
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
        logList.clear();

        List<Video> videoList = videoDao.getAll();


        for (int i = 0; i < videoList.size(); i++) {
            Log log = new Log();
            Video video = videoList.get(i);
            List<Tag> tagList = tagDao.getByVidId(video.getVideoId());

            String camera = deviceDao.getDeviceNameByVidId(video.getVideoId());

            log.setId(i);
            log.setVidId(video.getVideoId());
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

        tagDao.deleteTagToVidByVidId(vid_id);
        videoDao.deleteByVidId(vid_id);

        logList.remove(id);
        model.addAttribute("logList", logList);

        return "/logview";
    }

    @RequestMapping(params = "editTags")
    public String editTags(@RequestParam int tag_id, int vid_id, Log log, Tag tag, Model model){

        //Initialize database and create videoDao, tagDao object
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
        VideoDao videoDao = ctx.getBean("videoDaoImpl", VideoDao.class);
        TagDao tagDao = ctx.getBean("tagDaoImpl", TagDao.class);



        return "/logview";
    }


}
