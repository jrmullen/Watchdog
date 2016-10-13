package com.watchdog.dao;

/**
 * Created by jmullen on 10/10/16.
 */
public class Constants {

    //User queries
    public static final String CREATE_USER_QUERY = "insert into user (USER_FNAME, USER_LNAME, USER_EMAIL, USER_PASSWORD) values (?,?,?,?)";
    public static final String GET_BY_USER_ID_QUERY = "select USER_FNAME, USER_LNAME, USER_EMAIL from user where USER_ID = ?";
    public static final String UPDATE_USER_BY_ID_QUERY = "update user set USER_FNAME = ?, USER_LNAME = ?, USER_EMAIL = ?, USER_PASSWORD = ? where USER_ID = ?";
    public static final String DELETE_USER_BY_ID_QUERY = "delete from user where USER_ID=?";
    public static final String GET_ALL_USERS_QUERY = "select USER_ID, USER_FNAME, USER_LNAME, USER_EMAIL from user";

    //Device queries
    public static final String CREATE_DEVICE_QUERY = "insert into device (DEVICE_NAME, DEVICE_IP, DEVICE_MAC) values (?,?)";
    public static final String GET_BY_DEVICE_ID_QUERY = "select DEVICE_NAME, DEVICE_IP, DEVICE_MAC from device where DEVICE_ID = ?";
    public static final String UPDATE_BY_DEVICE_ID_QUERY = "update device set DEVICE_NAME = ?, DEVICE_IP = ?, DEVICE_MAC = ? where DEVICE_ID = ?";
    public static final String DELETE_DEVICE_BY_ID_QUERY = "delete from user where DEVICE_ID=?";
    public static final String GET_ALL_DEVICES_QUERY = "select DEVICE_NAME, DEVICE_IP, DEVICE_MAC from device";
}
