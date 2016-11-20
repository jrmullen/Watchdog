package com.watchdog.dao;

/**
 * Created by jmullen on 10/10/16.
 */
public class Constants {

    //User queries
    public static final String CREATE_USER_QUERY = "insert into user (PERMISS_ID, USER_FNAME, USER_LNAME, USER_EMAIL, USER_PASSWORD) values (?,?,?,?,?)";
    public static final String GET_BY_USER_ID_QUERY = "select USER_ID, PERMISS_ID, USER_FNAME, USER_LNAME, USER_EMAIL from user where USER_ID = ?";
    public static final String UPDATE_USER_BY_ID_QUERY = "update user set USER_FNAME = ?, USER_LNAME = ?, USER_EMAIL = ?, USER_PASSWORD = ? where USER_ID = ?";
    public static final String DELETE_USER_BY_ID_QUERY = "delete from user where USER_ID=?";
    public static final String GET_ALL_USERS_QUERY = "select USER_ID, PERMISS_ID, USER_FNAME, USER_LNAME, USER_EMAIL from user";
    public static final String GET_USER_BY_EMAIL_QUERY = "select USER_ID, PERMISS_ID, USER_FNAME, USER_LNAME, USER_EMAIL from user where USER_EMAIL = ?";
    public static final String SELECT_EMAIL_QUERY = "select USER_EMAIL from user where USER_EMAIL = ?";
    public static final String UPDATE_USER_PERMISSION_BY_ID_QUERY = "update user set PERMISS_ID = ? where USER_ID= ?";

    //Device queries
    public static final String GET_DEVICE_BY_DEVICE_MAC_QUERY = "select DEVICE_NAME from device where DEVICE_MAC = ?";
    public static final String CREATE_DEVICE_QUERY = "insert into device (DEVICE_NAME, DEVICE_MAC, DEVICE_ADDRESS, DEVICE_PORT) values (?,?,?,?)";
    public static final String GET_BY_DEVICE_ID_QUERY = "select DEVICE_ID, USER_ID, DEVICE_NAME, DEVICE_MAC, DEVICE_ADDRESS, DEVICE_PORT from device where DEVICE_ID = ?";
    public static final String UPDATE_BY_DEVICE_ID_QUERY = "update device set DEVICE_NAME = ?, DEVICE_MAC = ?, DEVICE_ADDRESS = ?, DEVICE_PORT = ? where DEVICE_ID = ?";
    public static final String DELETE_DEVICE_BY_ID_QUERY = "delete from device where DEVICE_ID = ?";
    public static final String SELECT_MAC_QUERY = "select DEVICE_MAC from device where DEVICE_MAC = ?";
    public static final String GET_ALL_DEVICES_QUERY = "select DEVICE_ID, USER_ID, DEVICE_NAME, DEVICE_MAC, DEVICE_ADDRESS, DEVICE_PORT from device";

    // Video queries
    public static final String CREATE_VIDEO_QUERY = "insert into video (USER_ID, VID_FILE_PATH, VID_LENGTH, VID_IS_COMPRESSED, VID_IS_ENCRYPTED, VID_SIZE_ON_DISK, VID_DATE, VID_TIME, VID_TITLE, VID_LOCATION, VID_DESCRIPTION, DEVICE_MAC) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    public static final String GET_VIDEO_BY_ID = "select VID_ID, USER_ID, VID_FILE_PATH, VID_LENGTH, VID_IS_COMPRESSED, VID_IS_ENCRYPTED, VID_SIZE_ON_DISK, VID_DATE, VID_TIME, VID_TITLE, VID_LOCATION, VID_DESCRIPTION, DEVICE_MAC from video where VID_ID = ?";
    public static final String GET_VIDEO_TITLE_BY_USER_ID = "select VID_TITLE from video where USER_ID = ?";
    public static final String GET_VIDEO_BY_VID_TITLE = "select VID_ID, USER_ID, VID_FILE_PATH, VID_LENGTH, VID_IS_COMPRESSED, VID_IS_ENCRYPTED, VID_SIZE_ON_DISK, VID_DATE, VID_TIME, VID_TITLE, VID_LOCATION, VID_DESCRIPTION, DEVICE_MAC from video VID_TITLE = ?";
    public static final String UPDATE_VIDEO_BY_ID_QUERY = "update video set VID_FILE_PATH = ?, VID_LENGTH = ?, VID_IS_COMPRESSED = ?, VID_IS_ENCRYPTED = ?, VID_SIZE_ON_DISK = ?, VID_DATE = ?, VID_TIME = ?, VID_TITLE = ?, VID_LOCATION = ?, VID_DESCRIPTION = ?, DEVICE_MAC = ? where VID_ID = ?";
    public static final String DELETE_VIDEO_BY_ID_QUERY = "delete from video where VID_ID=?";
    public static final String GET_ALL_VIDEOS_QUERY = "select VID_ID, USER_ID, VID_FILE_PATH, VID_LENGTH, VID_IS_COMPRESSED, VID_IS_ENCRYPTED, VID_SIZE_ON_DISK, VID_DATE, VID_TIME, VID_TITLE, VID_LOCATION, VID_DESCRIPTION, DEVICE_MAC from video";
    ;

    //Tag queries
    public static final String GET_ALL_TAGS_QUERY = "select TAG_ID, TAG_NAME from tag";
    public static final String DELETE_TAG_BY_ID_QUERY = "delete from tag where TAG_ID=?";
    public static final String UPDATE_TAG_NAME_BY_ID_QUERY = "update tag set TAG_NAME = ? where TAG_ID = ?";
    public static final String GET_TAG_BY_TAG_ID = "select TAG_ID, TAG_NAME from tag where TAG_ID = ?";
    public static final String GET_TAG_BY_TAG_NAME = "select TAG_ID, TAG_NAME from tag where TAG_NAME = ?";
    public static final String GET_TAG_ID_BY_TAG_NAME = "select TAG_ID from tag where TAG_NAME = ?";
    public static final String SELECT_TAG_NAME_QUERY = "select TAG_NAME from tag where TAG_NAME = ?";
    public static final String CREATE_TAG_QUERY = "insert into tag (TAG_NAME) values (?)";
    public static final String GET_TAG_ID_BY_VIDEO_ID = "select TAG_ID from tag_to_video where VID_ID = ?";
    public static final String DELETE_TAG_BY_VID_ID_QUERY = "delete from tag where VID_ID=?";


    //Tag-to-vid queries
    public static final String DELETE_TTV_BY_TAG_ID_QUERY = "delete from tag_to_video where tag_to_video.TAG_ID = ?";
    public static final String DELETE_TTV_BY_VID_ID_QUERY = "delete from tag_to_video where tag_to_video.VID_ID = ?";
    public static final String GET_TAG_ID_BY_VID_ID_QUERY = "select TAG_ID from tag_to_video where VID_ID = ?";
    public static final String GET_VID_ID_BY_TAG_ID_QUERY = "select VID_ID from tag_to_video where TAG_ID = ?";
    public static final String ADD_TAG_TO_VID_QUERY = "insert into tag_to_video (VID_ID, TAG_ID) values (?, ?)";
    public static final String GET_TAG_TO_VID_BY_VIDEO_ID_AND_TAG_ID_QUERY = "select VID_ID, TAG_ID from tag_to_video where VID_ID=? and TAG_ID=?";


    // Permissions
    public static final String CREATE_PERMISSIONS_QUERY = "insert into permissions (ROLE, PERMISS_DESCRIPTION) values (?,?)";
    public static final String GET_BY_PERMISSIONS_ID_QUERY = "select PERMISS_ID, ROLE, PERMISS_DESCRIPTION from permissions where PERMISS_ID = ?";
    public static final String UPDATE_BY_PERMISSIONS_ID_QUERY = "update permissions set ROLE = ?, PERMISS_DESCRIPTION = ? where PERMISS_ID = ?";
    public static final String DELETE_PERMISSIONS_BY_ID_QUERY = "delete from permissions where PERMISS_ID = ?";
    public static final String GET_ALL_PERMISSIONS_QUERY = "select PERMISS_ID, ROLE, PERMISS_DESCRIPTION from permissions";
}