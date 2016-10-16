package com.watchdog.dao;

/**
 * Created by jmullen on 10/10/16.
 */
public class Constants {

    //User queries
    public static final String CREATE_USER_QUERY = "insert into user (USER_FNAME, USER_LNAME, USER_EMAIL, USER_PASSWORD) values (?,?,?,?)";
    public static final String GET_BY_ID_QUERY = "select USER_FNAME, USER_LNAME, USER_EMAIL from user where USER_ID = ?";
    public static final String UPDATE_USER_BY_ID_QUERY = "update user set USER_FNAME = ?, USER_LNAME = ?, USER_EMAIL = ?, USER_PASSWORD = ? where USER_ID = ?";
    public static final String DELETE_USER_BY_ID_QUERY = "delete from user where USER_ID=?";
    public static final String GET_ALL_USERS_QUERY = "select USER_ID, USER_FNAME, USER_LNAME, USER_EMAIL from user";

    //Device queries


    // Video queries
    public static final String CREATE_VIDEO_QUERY = "insert into video (USER_ID, DEVICE_ID, VID_LENGTH, VID_IS_COMPRESSED, VID_IS_ENCRYPTED, VID_SIZE_ON_DISK, VID_DATE, VID_TIME, VID_TITLE, VID_LOCATION, VID_DESCRIPTION) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    public static final String GET_VIDEO_BY_USER_ID = "select VID_TITLE from video where USER_ID = ?";
    public static final String UPDATE_VIDEO_BY_ID_QUERY = "update video set VID_LENGTH = ?, VID_IS_COMPRESSED = ?, VID_IS_ENCRYPTED = ?, VID_SIZE_ON_DISK = ?, VID_DATE = ?, VID_TIME = ?, VID_TITLE = ?, VID_LOCATION = ?, VID_DESCRIPTION = ?";
    public static final String DELETE_VIDEO_BY_ID_QUERY = "delete from video where VID_ID=?";
    public static final String GET_ALL_VIDEOS_QUERY = "select VID_ID, USER_ID, DEVICE_ID, VID_LENGTH, VID_IS_COMPRESSED, VID_IS_ENCRYPTED, VID_SIZE_ON_DISK, VID_DATE, VID_TIME, VID_TITLE, VID_LOCATION, VID_DESCRIPTION from video";

    //Tag queries
    public static final String GET_ALL_TAGS_QUERY = "select TAG_ID, VID_ID, TAG_NAME from tag";
    public static final String DELETE_TAG_BY_ID_QUERY = "delete from tag where TAG_ID=?";
    public static final String UPDATE_TAG_BY_ID_QUERY = "update tag set TAG_ID = ?, VID_ID = ?, TAG_NAME = ?";
    public static final String GET_TAG_BY_TAG_ID = "select TAG_ID, VID_ID, TAG_NAME from tag where TAG_ID = ?";
    public static final String CREATE_TAG_QUERY = "insert into tag (VID_ID, TAG_NAME) values (?,?)";
}
