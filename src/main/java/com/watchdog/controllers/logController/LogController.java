package com.watchdog.controllers.logController;

/**
 * Created by Jeremy on 10/20/2016.
 */

import com.watchdog.business.Log;
import com.watchdog.business.Tag;
import com.watchdog.business.Video;
import com.watchdog.controllers.logController.CreateVideoTagRequest;
import com.watchdog.controllers.logController.EntityCreatedResponse;
import com.watchdog.dao.device.DeviceDao;
import com.watchdog.dao.tag.TagDao;
import com.watchdog.dao.video.VideoDao;
import com.watchdog.services.JsonConverterService;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
        boolean cameraNotExistsMessage =  false;

        for (int i = 0; i < videoList.size(); i++) {
            Log log = new Log();
            Video video = videoList.get(i);
            List<Tag> tagList = tagDao.getByVidId(video.getVideoId());

            try{
                String cameraName = deviceDao.getDeviceNameByVidId(video.getVideoId());

                log.setId(i);
                log.setVidId(video.getVideoId());
                log.setVideoFilePath(video.getFilePath() + "/" + video.getTitle());
                log.setDate(video.getDate());
                log.setStartTime(String.valueOf(video.getTime()));
                log.setLength(String.valueOf(video.getLength()));
                log.setCamera(cameraName);
                log.setTagList(tagList);
                log.setTags(log.getTagsString());
                logList.add(log);
            }
            catch(Exception e) {
                String deviceMac = videoDao.getVideoDeviceMacByVidId(video.getVideoId());

                log.setId(i);
                log.setVidId(video.getVideoId());
                log.setDate(video.getDate());
                log.setStartTime(String.valueOf(video.getTime()));
                log.setLength(String.valueOf(video.getLength()));
                log.setCamera(deviceMac);
                log.setTagList(tagList);
                log.setTags(log.getTagsString());
                logList.add(log);
                cameraNotExistsMessage = true;
                model.addAttribute("cameraNotExistsMessage", cameraNotExistsMessage);
            }
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

        tagDao.deleteTagFromVideo(tagId, videoId);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @RequestMapping(value="/videos/{videoId}/tags", method=RequestMethod.POST)
    public ResponseEntity<EntityCreatedResponse> createNewTag(
            @PathVariable("videoId") int videoId,
            @RequestBody CreateVideoTagRequest request) {

        int tagId = createTag(videoId, request.getTag());

        return ResponseEntity.status(HttpStatus.OK).body(new EntityCreatedResponse(tagId));
    }

    private int createTag(int videoId, String newTagName) {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
        TagDao tagDao = ctx.getBean("tagDaoImpl", TagDao.class);

        Tag newTag = new Tag();
        newTag.setTagName(newTagName);

        if (tagDao.checkTagExists(newTagName)) {
            System.out.println("tag " + newTagName + " already exists.");
            newTag.setTagId(tagDao.getTagIdByTagName(newTagName));
        } else {
            tagDao.save(newTag);
            newTag.setTagId(tagDao.getTagIdByTagName(newTagName));
        }

        if (tagDao.checkTagToVidExists(videoId, newTag.getTagId())) {
            System.out.println("Tag to video for video id " + videoId + " and tag id " + newTag.getTagId() + " already exists.");
        } else {
            tagDao.addTagToVid(videoId, newTag.getTagId());
        }

        return newTag.getTagId();
    }
}
