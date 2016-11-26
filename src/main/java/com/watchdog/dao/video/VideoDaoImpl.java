package com.watchdog.dao.video;

/**
 * Created by theNextThing on 10/12/2016.
 */

import com.watchdog.business.Video;
import com.watchdog.dao.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
        Object[] args = new Object[]{1, video.getFilePath(), video.getLength(), video.getIsCompressed(), video.getIsEncrypted(), video.getSize(),
                video.getDate(), video.getTime(), video.getTitle(), video.getLocation(), video.getDescription(), video.getDeviceMac()};

        int out = jdbcTemplate.update(Constants.CREATE_VIDEO_QUERY, args);

        if (out != 0) {
            System.out.println("Video " + video.getFilePath() + " " + video.getLength() + " "  +
                    video.getIsCompressed() + " " + video.getIsEncrypted() + " " + video.getSize()
                    + " " + video.getDate()  + " " + video.getTime()  + " " + video.getTitle()  +
                    " " + video.getLocation()  + " " + video.getDescription() + " " +
                    video.getDeviceMac() + " saved");

        } else System.out.println("Video " + video.getFilePath() + " " + video.getLength() + " "  +
                video.getIsCompressed() + " " + video.getIsEncrypted() + " " + video.getSize()
                + " " + video.getDate()  + " " + video.getTime()  + " " + video.getTitle()  +
                " " + video.getLocation()  + " " + video.getDescription() +  " " +
                video.getDeviceMac() + " failed to save");
    }

    @Override
    public Video getByVideoTitle(String videoTitle) {

        //using RowMapper anonymous class, we can create a separate RowMapper for reuse
        Video video = jdbcTemplate.queryForObject(Constants.GET_VIDEO_BY_VID_TITLE, new Object[]{videoTitle}, new RowMapper<Video>() {

            @Override
            public Video mapRow(ResultSet rs, int rowNum)
                    throws SQLException {
                Video video = new Video();

                video.setVideoId(rs.getInt("VID_ID"));
                video.setUserId(rs.getInt("USER_ID"));
                video.setFilePath(rs.getString("VID_FILE_PATH"));
                video.setLength(rs.getTime("VID_LENGTH"));
                video.setIsCompressed(rs.getBoolean("VID_IS_COMPRESSED"));
                video.setIsEncrypted(rs.getBoolean("VID_IS_ENCRYPTED"));
                video.setSize(rs.getLong("VID_SIZE_ON_DISK"));
                video.setDate(rs.getDate("VID_DATE"));
                video.setTime(rs.getTime("VID_TIME"));
                video.setTitle(rs.getString("VID_TITLE"));
                video.setLocation(rs.getDouble("VID_LOCATION"));
                video.setDescription(rs.getString("VID_DESCRIPTION"));
                video.setDeviceMac(rs.getString("DEVICE_MAC"));

                return video;
            }
        });
        return video;
    }

    @Override
    public Video getByVidId(int id) {

        //using RowMapper anonymous class, we can create a separate RowMapper for reuse
        Video video = jdbcTemplate.queryForObject(Constants.GET_VIDEO_BY_ID, new Object[]{id}, new RowMapper<Video>() {

            @Override
            public Video mapRow(ResultSet rs, int rowNum)
                    throws SQLException {
                Video video = new Video();

                video.setVideoId(rs.getInt("VID_ID"));
                video.setUserId(rs.getInt("USER_ID"));
                video.setFilePath(rs.getString("VID_FILE_PATH"));
                video.setLength(rs.getTime("VID_LENGTH"));
                video.setIsCompressed(rs.getBoolean("VID_IS_COMPRESSED"));
                video.setIsEncrypted(rs.getBoolean("VID_IS_ENCRYPTED"));
                video.setSize(rs.getLong("VID_SIZE_ON_DISK"));
                video.setDate(rs.getDate("VID_DATE"));
                video.setTime(rs.getTime("VID_TIME"));
                video.setTitle(rs.getString("VID_TITLE"));
                video.setLocation(rs.getDouble("VID_LOCATION"));
                video.setDescription(rs.getString("VID_DESCRIPTION"));
                video.setDeviceMac(rs.getString("DEVICE_MAC"));

                return video;
            }
        });
        return video;
    }

    @Override
    public String getVideoDeviceMacByVidId(int id) {

        //using RowMapper anonymous class, we can create a separate RowMapper for reuse
        Video video = jdbcTemplate.queryForObject(Constants.GET_DEVICE_MAC_BY_VID_ID, new Object[]{id}, new RowMapper<Video>() {

            @Override
            public Video mapRow(ResultSet rs, int rowNum)
                    throws SQLException {
                Video video = new Video();
                video.setDeviceMac(rs.getString("DEVICE_MAC"));
                return video;
            }
        });
        return video.getDeviceMac();
    }

    @Override
    public void update(Video video, int videoId) {

        video.setVideoId(videoId);
        Object[] args = new Object[]{video.getFilePath(), video.getLength(), video.getIsCompressed(), video.getIsEncrypted(), video.getSize(),
                video.getDate(), video.getTime(), video.getTitle(), video.getLocation(), video.getDescription(), video.getDeviceMac(), videoId};

        System.out.println("Video: " + video.getFilePath() + " " + video.getLength() + " "  +
                video.getIsCompressed() + " " + video.getIsEncrypted() + " " + video.getSize()
                + " " + video.getDate()  + " " + video.getTime()  + " " + video.getTitle()  +
                " " + video.getLocation()  + " " + video.getDescription() + " " +
                video.getDeviceMac());

        int out = jdbcTemplate.update(Constants.UPDATE_VIDEO_BY_ID_QUERY, args);
        if (out != 0) {
            System.out.println("Video updated with id= " + video.getVideoId());
        } else System.out.println("No Video found with id= " + video.getVideoId());
    }


    @Override
    public void deleteByVidId(int videoId) {

        int out = jdbcTemplate.update(Constants.DELETE_VIDEO_BY_ID_QUERY, videoId);
        if (out != 0) {
            System.out.println("Video deleted with id= " + videoId);
        } else System.out.println("No Video found with id= " + videoId);
    }

    @Override
    public void deleteByName(String videoName) {

        int out = jdbcTemplate.update(Constants.DELETE_VIDEO_BY_TITLE_QUERY, videoName);
        if (out != 0) {
            System.out.println("Video deleted with name = " + videoName);
        } else System.out.println("No Video found with name = " + videoName);
    }

    @Override
    public List<Video> getAll() {

        List<Video> videoList = new ArrayList<Video>();

        List<Map<String, Object>> videoRows = jdbcTemplate.queryForList(Constants.GET_ALL_VIDEOS_QUERY);

        for (Map<String, Object> videoRow : videoRows) {
            Video video = new Video();

            video.setVideoId(Integer.parseInt(String.valueOf(videoRow.get("VID_ID"))));
            video.setUserId(Integer.parseInt(String.valueOf(videoRow.get("USER_ID"))));
            video.setFilePath((String.valueOf(videoRow.get("VID_FILE_PATH"))));
            video.setLength(Time.valueOf(String.valueOf(videoRow.get("VID_LENGTH"))));
            video.setIsCompressed(Boolean.valueOf(String.valueOf(videoRow.get("VID_IS_COMPRESSED")))); // will error?, stored in db as tinyint
            video.setIsEncrypted(Boolean.valueOf(String.valueOf(videoRow.get("VID_IS_ENCRYPTED")))); // will error?, stored in db as tinyint
            video.setSize(Long.valueOf(String.valueOf(videoRow.get("VID_SIZE_ON_DISK"))));
            video.setDate(Date.valueOf(String.valueOf(videoRow.get("VID_DATE"))));
            video.setTime(Time.valueOf(String.valueOf(videoRow.get("VID_TIME"))));
            video.setTitle(String.valueOf(videoRow.get("VID_TITLE")));
            video.setLocation(Double.valueOf(String.valueOf(videoRow.get("VID_LOCATION"))));
            video.setDescription(String.valueOf(videoRow.get("VID_DESCRIPTION")));
            video.setDeviceMac(String.valueOf(videoRow.get("DEVICE_MAC")));

            videoList.add(video);
        }
        return videoList;
    }

}
