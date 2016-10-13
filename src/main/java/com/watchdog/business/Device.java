package com.watchdog.business;

import javax.validation.constraints.NotNull;

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

    @NotNull
    String deviceName;

    public String getDeviceIp() {

        return deviceIp;
    }

    public void setDeviceIp(String deviceIp) {

        this.deviceIp = deviceIp;
    }

    @NotNull
    String deviceIp;


    public String getDeviceMac() {

        return deviceMac;
    }

    public void setDeviceMac(String deviceMac) {

        this.deviceMac = deviceMac;
    }

    @NotNull
    String deviceMac;

    public void setId(int id) {

        this.id = id;
    }

    public int getId() {
        return id;
    }

}
