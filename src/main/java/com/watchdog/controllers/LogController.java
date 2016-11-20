package com.watchdog.controllers;

/**
 * Created by Jeremy on 10/20/2016.
 */

import com.watchdog.business.Log;
import com.watchdog.business.Tag;
import com.watchdog.business.Video;
import com.watchdog.dao.device.DeviceDao;
import com.watchdog.dao.tag.TagDao;
import com.watchdog.dao.video.VideoDao;
import com.watchdog.services.JsonConverterService;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/logview", method = { RequestMethod.GET, RequestMethod.POST })
public class LogController {

    List<Log> logList = new ArrayList<>();
    JsonConverterService jsonConverterService = new JsonConverterService();

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

        String logListJson = jsonConverterService.objectToJson(logList);
        System.out.println(logListJson);

        //export list to model
        model.addAttribute("logListJson", logListJson);
        model.addAttribute("logList", logList);

        //redirect to logview page
        return "/logview";
    }

    @RequestMapping(value="/video/{videoId}", method=RequestMethod.DELETE)
    public ResponseEntity deleteVideo(
            @PathVariable("videoId") int videoId) {

        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
        VideoDao videoDao = ctx.getBean("videoDaoImpl", VideoDao.class);
        TagDao tagDao = ctx.getBean("tagDaoImpl", TagDao.class);

        tagDao.deleteTagToVidByVidId(videoId);
        videoDao.deleteByVidId(videoId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


    @RequestMapping(value="/tag/{videoId}/{tagId}", method=RequestMethod.DELETE)
    public ResponseEntity deleteTag(
            @PathVariable("tagId") int tagId,
            @PathVariable("videoId") int videoId) {

        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
        TagDao tagDao = ctx.getBean("tagDaoImpl", TagDao.class);

        tagDao.deleteTagToVidByTagId(tagId);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @RequestMapping(value="/tag/{videoId}/{newTagName}", method=RequestMethod.PUT)
    public ResponseEntity createNewTag(
            @PathVariable("videoId") int videoId,
            @PathVariable("newTagName") String newTagName) {

        createTag(videoId, newTagName);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    private void createTag(int videoId, String newTagName) {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
        TagDao tagDao = ctx.getBean("tagDaoImpl", TagDao.class);

        Tag newTag = new Tag();
        newTag.setTagName(newTagName);
        System.out.println(videoId);

        if (tagDao.checkTagExists(newTagName)) {
            System.out.println("tag " + newTagName + " already exists.");
            newTag.setTagId(tagDao.getTagIdByTagName(newTagName));
        } else {
            System.out.println(newTag.getTagId());
            tagDao.save(newTag);
            newTag.setTagId(tagDao.getTagIdByTagName(newTagName));
        }

        if (tagDao.checkTagToVidExists(videoId, newTag.getTagId())) {
            System.out.println("Tag to video for video id " + videoId + " and tag id " + newTag.getTagId() + " already exists.");
        } else {
            tagDao.addTagToVid(videoId, newTag.getTagId());
        }
    }
}
