package com.watchdog.dao;

/**
 * Created by theNextThing on 10/12/2016.
 */

import com.watchdog.business.Video;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.sql.*;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class VideoDaoImpl implements VideoDao {

    @Autowired
    private DataSource dataSource;

    private JdbcTemplate jdbcTemplate;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(this.dataSource);
    }

    @Override
    public void save(Video video) {
        Object[] args = new Object[]{video.getLength(), video.getIsCompressed(), video.getIsEncrypted(), video.getSize(),
            video.getDate(), video.getTime(), video.getTitle(), video.getLocation(), video.getDescription()};

        int out = jdbcTemplate.update(Constants.CREATE_VIDEO_QUERY, args);

        if (out != 0) {
            System.out.println("Video " + video.getLength() + " "  + video.getIsCompressed() + " " +
                            video.getIsEncrypted() + " " + video.getSize()  + " " + video.getDate()  + " " +
                            video.getTime()  + " " + video.getTitle()  + " " + video.getLocation()  + " " +
                            video.getDescription() + " saved");

        } else System.out.println("Video " + video.getLength() + " "  + video.getIsCompressed() + " " +
                video.getIsEncrypted() + " " + video.getSize()  + " " + video.getDate()  + " " +
                video.getTime()  + " " + video.getTitle()  + " " + video.getLocation()  + " " +
                video.getDescription() + " failed");
    }

    @Override
    public Video getById(int id) {

        //using RowMapper anonymous class, we can create a separate RowMapper for reuse
        Video video = jdbcTemplate.queryForObject(Constants.GET_VIDEO_BY_ID, new Object[]{id}, new RowMapper<Video>() {

            @Override
            public Video mapRow(ResultSet rs, int rowNum)
                    throws SQLException {
                Video video = new Video();

                video.setLength(rs.getTime("VID_LENGTH"));
                video.setIsCompressed(rs.getBoolean("VID_IS_COMPRESSED"));
                video.setIsEncrypted(rs.getBoolean("VID_IS_ENCRYPTED"));
                video.setSize(rs.getLong("VID_SIZE_ON_DISK"));
                video.setDate(rs.getDate("VID_DATE"));
                video.setTime(rs.getTime("VID_TIME"));
                video.setTitle(rs.getString("VID_TITLE"));
                video.setLocation(rs.getString("VID_LOCATION"));
                video.setDescription(rs.getString("VID_DESCRIPTION"));

                return video;
            }
        });
        return video;
    }

    @Override
    public void update(Video video) {

        Object[] args = new Object[]{video.getLength(), video.getIsCompressed(), video.getIsEncrypted(), video.getSize(),
                video.getDate(), video.getTime(), video.getTitle(), video.getLocation(), video.getDescription()};

        int out = jdbcTemplate.update(Constants.UPDATE_VIDEO_BY_ID_QUERY, args);
        if (out != 0) {
            System.out.println("Video updated with id= " + video.getVidId());
        } else System.out.println("No Video found with id= " + video.getVidId());
    }


    @Override
    public void deleteById(int vid_id) {

        String query = "delete from video where VID_ID=?";

        int out = jdbcTemplate.update(Constants.DELETE_VIDEO_BY_ID_QUERY, vid_id);
        if (out != 0) {
            System.out.println("Video deleted with id= " + vid_id);
        } else System.out.println("No Video found with id= " + vid_id);
    }

    @Override
    public List<Video> getAll() {

        List<Video> videoList = new ArrayList<Video>();

        List<Map<String, Object>> videoRows = jdbcTemplate.queryForList(Constants.GET_ALL_VIDEOS_QUERY);

        for (Map<String, Object> videoRow : videoRows) {
            Video video = new Video();

            video.setVidId(Integer.parseInt(String.valueOf(videoRow.get("VID_ID"))));
            video.setUserId(Integer.parseInt(String.valueOf(videoRow.get("USER_ID"))));
            video.setDeviceId(Integer.parseInt(String.valueOf(videoRow.get("DEVICE_ID"))));
            video.setLength(Time.valueOf(String.valueOf(videoRow.get("VID_LENGTH"))));
            video.setIsCompressed(Boolean.valueOf(String.valueOf(videoRow.get("VID_IS_COMPRESSED")))); // will error?, stored in db as tinyint
            video.setIsEncrypted(Boolean.valueOf(String.valueOf(videoRow.get("VID_IS_ENCRYPTED")))); // will error?, stored in db as tinyint
            video.setSize(Long.valueOf(String.valueOf(videoRow.get("VID_SIZE_ON_DISK"))));
            video.setDate(Date.valueOf(String.valueOf(videoRow.get("VID_DATE"))));
            video.setTime(Time.valueOf(String.valueOf(videoRow.get("VID_TIME"))));
            video.setTitle(String.valueOf(videoRow.get("VID_TITLE")));
            video.setLocation(String.valueOf(videoRow.get("VID_LOCATION")));
            video.setDescription(String.valueOf(videoRow.get("VID_DESCRIPTION")));

            videoList.add(video);
        }
        return videoList;
    }

}

