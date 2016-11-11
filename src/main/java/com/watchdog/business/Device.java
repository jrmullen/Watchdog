package com.watchdog.business;

import javax.validation.constraints.NotNull;

/**
 * Created by BBuck on 10/4/16.
 */

public class Device {

    private int id,
            userId;

    @NotNull
    private String deviceName;

    @NotNull
    private String deviceMac;

    @NotNull
    private String deviceAddress;

    private String devicePort;

    private String deviceUrl;

    public void setDevicePort(String devicePort) {this.devicePort = devicePort; }

    public String getDevicePort() {
        return devicePort;
    }

    public void setDeviceUrl(String deviceUrl){
        this.deviceUrl = deviceUrl;
    }

    public String getDeviceUrl() {
        return deviceUrl;
    }

    public void setId(int id) { this.id = id; }

    public int getId() { return id; }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getDeviceName() { return deviceName; }

    public void setDeviceName(String deviceName) { this.deviceName = deviceName; }

    public String getDeviceMac() { return deviceMac; }

    public void setDeviceMac(String deviceMac) { this.deviceMac = deviceMac; }

    public String getDeviceAddress() { return deviceAddress; }

    public void setDeviceAddress(String deviceAddress) { this.deviceAddress = deviceAddress; }

    public String buildDeviceUrl(String deviceIp, String devicePort) {
        String deviceUrl = "http://" + deviceIp + ":" + devicePort;
        setDeviceUrl(deviceUrl);
        return deviceUrl;
    }
}