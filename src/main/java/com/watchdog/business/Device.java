package com.watchdog.business;

import javax.validation.constraints.NotNull;

/**
 * Created by BBuck on 10/4/16.
 */

public class Device {

    private int id,
            userId,
            permissId;

    @NotNull
    private String deviceName;

    private String deviceMac;

    @NotNull
    private String deviceAddress;

    @NotNull
    private int devicePort;

    @NotNull
    private String deviceIp;

    private String deviceUrl;

    public void setDevicePort(int devicePort) {
        this.devicePort = devicePort;
    }

    public int getDevicePort() {
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

    public int getPermissId() {
        return permissId;
    }

    public void setPermissId(int permissId) {
        this.permissId = permissId;
    }

    public String getDeviceName() { return deviceName; }

    public void setDeviceName(String deviceName) { this.deviceName = deviceName; }

    public String getDeviceMac() { return deviceMac; }

    public void setDeviceMac(String deviceMac) { this.deviceMac = deviceMac; }

    public String getDeviceAddress() { return deviceAddress; }

    public void setDeviceAddress(String deviceAddress) { this.deviceAddress = deviceAddress; }

    public String buildDeviceUrl(String deviceIp, int devicePort) {
        String deviceUrl = "http://" + deviceIp + ":" + devicePort;
        setDeviceUrl(deviceUrl);
        return deviceUrl;}

    public String getDeviceIp() { return deviceIp; }

    public void setDeviceIp(String deviceIp) { this.deviceIp = deviceIp; }
    }
}
