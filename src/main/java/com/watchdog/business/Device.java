package com.watchdog.business;

import javax.validation.constraints.NotNull;

/**
 * Created by BBuck on 10/4/16.
 */
public class Device {

    public void setId(int id) {

        this.id = id;
    }

    public int getId() {
        return id;
    }


    private int id;

    public String getDeviceName() {

        return deviceName;
    }

    public void setDeviceName(String deviceName) {

        this.deviceName = deviceName;
    }

    @NotNull
    private String deviceName;

    public String getDeviceMac() {

        return deviceMac;
    }

    public void setDeviceMac(String deviceMac) {

        this.deviceMac = deviceMac;
    }

    @NotNull
    private String deviceMac;


    public String getDeviceIp() {

        return deviceIp;
    }

    public void setDeviceIp(String deviceIp) {

        this.deviceIp = deviceIp;
    }

    @NotNull
    private String deviceIp;


}
