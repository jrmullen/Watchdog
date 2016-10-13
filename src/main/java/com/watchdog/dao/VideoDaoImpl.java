package com.watchdog.dao;

import com.watchdog.business.User;
import com.watchdog.business.Video;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
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

    //create
    public void save(Video video){
        String query = "insert into video (VID_LENGTH, VID_IS_COMPRESSED, VID_IS_ENCRYPTED, VID_SIZE_ON_DISK, VID_DATE, VID_TIME, VID_TITLE, VID_LOCATION, VID_DESCRIPTION) values (?,?,?,?,?,?,?,?,?)";

        Object[] args = new Object[]{video.getLength(), video.getIsCompressed(), video.getIsEncrypted(), video.getSize(), video.getDate(), video.getTime(), video.getTitle(), video.getLocation(), video.getDescription()};

        int out = jdbcTemplate.update(query, args);

        if (out != 0) {
            System.out.println("Video saved");
        } else System.out.println("Video save failed");
    }

    //read
    public Video getByID(int id){
        String query = "select * from video where VID_ID = ?";

        //using RowMapper anonymous class, we can create a separate RowMapper for reuse
        Video video = jdbcTemplate.queryForObject(query, new Object[]{id}, new RowMapper<Video>() {

            @Override
            public Video mapRow(ResultSet rs, int rowNum)
                    throws SQLException {
                Video video = new Video();
                video.setID(rs.getInt("VID_ID"));
                video.setLength(rs.getString("VID_LENGTH"));
                video.setIsCompressed(rs.getBoolean("VID_IS_COMPRESSED"));
                video.setIsEncrypted(rs.getBoolean("VID_IS_ENCRYPTED"));
                video.setSize(rs.getLong("VID_SIZE_ON_DISK"));
                video.setDate(rs.getString("VID_DATE"));
                video.setTime(rs.getString("VID_TIME"));
                video.setTitle(rs.getString("VID_TITLE"));
                video.setLocation(rs.getString("VID_LOCATION"));
                video.setDescription(rs.getString("VID_DESCRIPTION"));
                return video;
            }
        });
        return video;

    }

    //update
    public void update(Video video){
        String query = "update video set VID_LENGTH = ?, VID_IS_COMPRESSED = ?, VID_IS_ENCRYPTED = ?, VID_SIZE_ON_DISK = ?, VID_DATE = ?, VID_TIME = ?, VID_TITLE = ?, VID_LOCATION = ?, VID_DESCRIPTION = ? where VID_ID = ?";

        Object[] args = new Object[]{video.getLength(), video.getIsCompressed(), video.getIsEncrypted(), video.getSize(), video.getDate(), video.getTime(), video.getTitle(), video.getLocation(), video.getDescription(), video.getID()};

        int out = jdbcTemplate.update(query, args);
        if (out != 0) {
            System.out.println("Video updated with ID = " + video.getID());
        } else System.out.println("No User found with ID = " + video.getID());
    }

    //delete
    public void deleteByID(int id){

        String query = "delete from video where VID_ID=?";

        int out = jdbcTemplate.update(query, id);
        if (out != 0) {
            System.out.println("Video deleted with ID = " + id);
        } else System.out.println("No video found with ID = " + id);
    }

    //get all
    @Override
    public List<Video> getAll(){
        String query = "select VID_ID, VID_DATE, VID_TIME, VID_LENGTH, VID_TITLE, VID_LOCATION, VID_DESCRIPTION, VID_SIZE_ON_DISK, VID_IS_COMPRESSED, VID_IS_ENCRYPTED from video";

        List<Video> videoList = new ArrayList<Video>();

        List<Map<String, Object>> videoRows = jdbcTemplate.queryForList(query);

        for (Map<String, Object> videoRow : videoRows) {
            Video video = new Video();
            video.setID(Integer.parseInt(String.valueOf(videoRow.get("VID_ID"))));
            video.setDate(String.valueOf(videoRow.get("VID_DATE")));
            video.setTime(String.valueOf(videoRow.get("VID_TIME")));
            video.setLength(String.valueOf(videoRow.get("VID_LENGTH")));
            video.setTitle(String.valueOf(videoRow.get("VID_TITLE")));
            video.setLocation(String.valueOf(videoRow.get("VID_LOCATION")));
            video.setDescription(String.valueOf(videoRow.get("VID_DESCRIPTION")));
            video.setSize(Long.parseLong(String.valueOf(videoRow.get("VID_SIZE_ON_DISK"))));
            video.setIsCompressed(Boolean.parseBoolean(String.valueOf(videoRow.get("VID_IS_COMPRESSED"))));
            video.setIsEncrypted(Boolean.parseBoolean(String.valueOf(videoRow.get("VID_IS_ENCRYPTED"))));
            videoList.add(video);
        }

        return videoList;
    }




}

