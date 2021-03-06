package com.watchdog.dao.tag;

import com.watchdog.business.Tag;
import com.watchdog.business.Video;
import com.watchdog.dao.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TagDaoImpl implements TagDao {

    @Autowired
    private DataSource dataSource;

    private JdbcTemplate jdbcTemplate;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(this.dataSource);
    }

    //Create
    @Override
    public void save(Tag tag) {
        Object[] args = new Object[]{tag.getTagName()};

        int out = jdbcTemplate.update(Constants.CREATE_TAG_QUERY, args);

        if (out != 0) {
            System.out.println("Tag " + tag.getTagName() + " "  + tag.getTagId() + " saved");

        } else System.out.println("Tag " + tag.getTagName() + " "  + tag.getTagId() + " failed");
    }

    //Read
    @Override
    public Tag getByTagId(int id) {

        //using RowMapper anonymous class, we can create a separate RowMapper for reuse
        Tag tag = jdbcTemplate.queryForObject(Constants.GET_TAG_BY_TAG_ID, new Object[]{id}, new RowMapper<Tag>() {

            @Override
            public Tag mapRow(ResultSet rs, int rowNum)
                    throws SQLException {

                Tag tag = new Tag();
                tag.setTagId(rs.getInt("TAG_ID"));
                tag.setTagName(rs.getString("TAG_NAME"));
                return tag;
            }
        });
        return tag;
    }

    @Override
    public Tag getByTagName(String name) {

        //using RowMapper anonymous class, we can create a separate RowMapper for reuse
        Tag tag = jdbcTemplate.queryForObject(Constants.GET_TAG_BY_TAG_NAME, new Object[]{name}, new RowMapper<Tag>() {

            @Override
            public Tag mapRow(ResultSet rs, int rowNum)
                    throws SQLException {

                Tag tag = new Tag();
                tag.setTagId(rs.getInt("TAG_ID"));
                tag.setTagName(rs.getString("TAG_NAME"));
                return tag;
            }
        });
        return tag;
    }

    @Override
    public int getTagIdByTagName(String tagName) {

        Tag tag = this.getByTagName(tagName);
        System.out.println("Tag with name " + tagName + " has ID: " + tag.getTagId());
        return tag.getTagId();
    }

    @Override
    public List<Tag> getByVidId(int id){
        List<Tag> tagList = new ArrayList<>();

        List<Map<String, Object>> tagRows = jdbcTemplate.queryForList(Constants.GET_TAG_ID_BY_VIDEO_ID, id);

        for (Map<String, Object> tagRow : tagRows) {
            Tag tag = this.getByTagId(Integer.parseInt(String.valueOf(tagRow.get("TAG_ID"))));
            tagList.add(tag);
        }

        return tagList;
    }

    //Update
    @Override
    public void update(Tag tag) {

        Object[] args = new Object[]{tag.getTagName(), tag.getTagId()};

        int out = jdbcTemplate.update(Constants.UPDATE_TAG_NAME_BY_ID_QUERY, args);
        if (out != 0) {
            System.out.println("Tag updated with id= " + tag.getTagId() + " name= " + tag.getTagName());
        } else System.out.println("No Tag found with id= " + tag.getTagId());
    }

    @Override
    public void deleteTagFromVideo(int tagId, int videoId) {
        int out = jdbcTemplate.update(Constants.DELETE_VIDEO_TAG_QUERY, tagId, videoId);
        if (out != 0) {
            System.out.println("Tag to video tag " + tagId + " deleted from video " + videoId);
        } else System.out.println("No Tag to video found for tag " + tagId + " and video " + videoId);
    }

    @Override
    public boolean checkTagExists(String newTag) {
        try {
            this.getByTagName(newTag);
            return true;
        }
        catch(Exception e) {
            return false;
        }
  }

    @Override
    public boolean checkVidIdExistsInTagToVideo(int videoId) {
        try {
            Tag tag = jdbcTemplate.queryForObject(Constants.GET_VID_ID_QUERY, new Object[]{videoId}, new RowMapper<Tag>() {

                @Override
                public Tag mapRow(ResultSet rs, int rowNum)
                        throws SQLException {
                    Tag tag = new Tag();
                    tag.setTagId(rs.getInt("VID_ID"));
                    return tag;
                }
            });
            return true;
        }
        catch(IncorrectResultSizeDataAccessException e) {
            if (e.getActualSize() >= 2) {
                return true;
            }
            return false;
        }
        catch(Exception e1) {
            return false;
        }
    }

    @Override
    public boolean checkTagToVidExists(int videoId, int tagId) {
        try {
            Tag tag = jdbcTemplate.queryForObject(Constants.GET_TAG_TO_VID_BY_VIDEO_ID_AND_TAG_ID_QUERY, new Object[]{videoId, tagId}, new RowMapper<Tag>() {

                @Override
                public Tag mapRow(ResultSet rs, int rowNum)
                        throws SQLException {
                    Tag tag = new Tag();
                    tag.setTagId(rs.getInt("TAG_ID"));
                    return tag;
                }
            });
            return true;
        }
        catch(Exception e) {
            return false;
        }
    }

    @Override
    public void addTagToVid(int videoId, int tagId) {
        int out = jdbcTemplate.update(Constants.ADD_TAG_TO_VID_QUERY, videoId, tagId);
        if (out != 0) {
            System.out.println("Tag with tagId: " + tagId + " added to video with videoId: " + videoId);
        } else {
            System.out.println("Unable to add tagId: " + tagId + " to video videoId: " + videoId);
        }
    }

    @Override
    public void deleteTagToVidByTagId(int tag_id){
        int out = jdbcTemplate.update(Constants.DELETE_TTV_BY_TAG_ID_QUERY, tag_id);
        if(out != 0){
            System.out.println("Deleted with tag_id = " + tag_id + " from tag to video table.");
        } else {
            System.out.println("Unable to delete tag_id = " + tag_id + " from tag to video table.");
        }
    }

    @Override
    public void deleteTagToVidByVidId(int vid_id){
        int out = jdbcTemplate.update(Constants.DELETE_TTV_BY_VID_ID_QUERY, vid_id);
        if(out != 0){
            System.out.println("TTV deleted with vid_id = " + vid_id);
        } else {
            System.out.println("Unable to delete TTV with vid_id = " + vid_id);
        }
    }

    //Get All
    @Override
    public List<Tag> getAll() {

        List<Tag> tagList = new ArrayList<Tag>();

        List<Map<String, Object>> tagRows = jdbcTemplate.queryForList(Constants.GET_ALL_TAGS_QUERY);

        for (Map<String, Object> tagRow : tagRows) {
            Tag tag = new Tag();

            tag.setTagId(Integer.parseInt(String.valueOf(tagRow.get("TAG_ID"))));
            tag.setTagName(String.valueOf(tagRow.get("TAG_NAME")));
            tagList.add(tag);
        }
        return tagList;
    }
}
