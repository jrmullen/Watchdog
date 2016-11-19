package com.watchdog.dao.tag;

import com.watchdog.business.Tag;
import com.watchdog.dao.Constants;
import com.watchdog.dao.tag.TagDao;
import org.springframework.beans.factory.annotation.Autowired;
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

    //Delete
    @Override
    public void deleteByTagId(int tag_id) {
        this.deleteTagToVidByTagId(tag_id);
        int out = jdbcTemplate.update(Constants.DELETE_TAG_BY_ID_QUERY, tag_id);
        if (out != 0) {
            System.out.println("Tag deleted with tag_id= " + tag_id);
        } else System.out.println("No Tag found with tag_id= " + tag_id);
    }

    @Override
    public boolean checkTagExists(String newTag) {
        try {
            Tag tag = jdbcTemplate.queryForObject(Constants.SELECT_TAG_NAME_QUERY, new Object[]{newTag}, new RowMapper<Tag>() {

                @Override
                public Tag mapRow(ResultSet rs, int rowNum)
                        throws SQLException {
                    Tag tag = new Tag();
                    Tag.setTagName(rs.getString("TAG_NAME"));
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
    public void deleteTagToVidByTagId(int tag_id){
        int out = jdbcTemplate.update(Constants.DELETE_TTV_BY_TAG_ID_QUERY, tag_id);
        if(out != 0){
            System.out.println("TTV deleted with tag_id = " + tag_id);
        }else{
            System.out.println("Unable to delete TTV with tag_id = " + tag_id);
        }
    }

    @Override
    public void deleteTagToVidByVidId(int vid_id){
        int out = jdbcTemplate.update(Constants.DELETE_TTV_BY_VID_ID_QUERY, vid_id);
        if(out != 0){
            System.out.println("TTV deleted with vid_id = " + vid_id);
        }else{
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
