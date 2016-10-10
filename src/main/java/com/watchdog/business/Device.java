package com.watchdog.business;

/**
 * Created by BBuck on 10/4/16.
 */
public class Device {

    private int id;

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    String deviceName;


    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    String deviceType;

    public void setId(int id) {
        this.id = id;
    }
    public int getId() {
        return id;
    }
}
