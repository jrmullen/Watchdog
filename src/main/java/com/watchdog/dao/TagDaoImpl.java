package com.watchdog.dao;

import com.watchdog.business.Tag;
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
        Object[] args = new Object[]{tag.getVid_id(), tag.getTag_name()};

        int out = jdbcTemplate.update(Constants.CREATE_TAG_QUERY, args);

        if (out != 0) {
            System.out.println("Tag " + tag.getTag_name() + " "  + tag.getTag_id() + " " +
                    tag.getVid_id() + " saved");

        } else System.out.println("Tag " + tag.getTag_name() + " "  + tag.getTag_id() + " " +
                tag.getVid_id() + " failed");
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
                tag.setTag_id(rs.getInt("TAG_ID"));
                tag.setVid_id((rs.getInt("VID_ID")));
                tag.setTag_name(rs.getString("TAG_NAME"));
                return tag;
            }
        });
        return tag;
    }

    public List<Tag> getByVidId(int id){
        List<Tag> tagList = new ArrayList<>();

        List<Map<String, Object>> tagRows = jdbcTemplate.queryForList(Constants.GET_TAG_BY_VIDEO_ID, id);

        for (Map<String, Object> tagRow : tagRows) {
            Tag tag = new Tag();

            tag.setTag_id(Integer.parseInt(String.valueOf(tagRow.get("TAG_ID"))));
            tag.setVid_id(Integer.parseInt(String.valueOf(tagRow.get("VID_ID"))));
            tag.setTag_name(String.valueOf(tagRow.get("TAG_NAME")));
            tagList.add(tag);
        }

        return tagList;
    }

    //Update
    @Override
    public void update(Tag tag) {

        Object[] args = new Object[]{tag.getTag_id(), tag.getVid_id(), tag.getTag_name()};

        int out = jdbcTemplate.update(Constants.UPDATE_TAG_BY_ID_QUERY, args);
        if (out != 0) {
            System.out.println("Tag updated with id= " + tag.getTag_id());
        } else System.out.println("No Tag found with id= " + tag.getTag_id());
    }

    //Delete
    @Override
    public void deleteById(int tag_id) {

        int out = jdbcTemplate.update(Constants.DELETE_TAG_BY_ID_QUERY, tag_id);
        if (out != 0) {
            System.out.println("Tag deleted with id= " + tag_id);
        } else System.out.println("No Tag found with id= " + tag_id);
    }

    @Override
    public void deleteByVidId(int vid_id) {

        int out = jdbcTemplate.update(Constants.DELETE_TAG_BY_VID_ID_QUERY, vid_id);
        if (out != 0) {
            System.out.println("Tag deleted with id= " + vid_id);
        } else System.out.println("No Tag found with id= " + vid_id);
    }

    //Get All
    @Override
    public List<Tag> getAll() {

        List<Tag> tagList = new ArrayList<Tag>();

        List<Map<String, Object>> tagRows = jdbcTemplate.queryForList(Constants.GET_ALL_TAGS_QUERY);

        for (Map<String, Object> tagRow : tagRows) {
            Tag tag = new Tag();

            tag.setTag_id(Integer.parseInt(String.valueOf(tagRow.get("TAG_ID"))));
            tag.setVid_id(Integer.parseInt(String.valueOf("VID_ID")));
            tag.setTag_name(String.valueOf(tagRow.get("TAG_NAME")));
            tagList.add(tag);
        }
        return tagList;
    }
}
