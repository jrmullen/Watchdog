package com.watchdog.business;

import javax.validation.constraints.NotNull;

/**
 * Created by BBuck on 10/4/16.
 */
public class Permissions {

    private int id,
            userId,
            permissId;

    @NotNull
    private String permissionsName;
    @NotNull
    private String permissionsMac;
    @NotNull
    private String permissionsIp;

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

    public String getPermissionsName() { return permissionsName; }

    public void setPermissionsName(String permissionsName) { this.permissionsName = permissionsName; }

    public String getPermissionsMac() { return permissionsMac; }

    public void setPermissionsMac(String permissionsMac) { this.permissionsMac = permissionsMac; }

    public String getPermissionsIp() { return permissionsIp; }

    public void setPermissionsIp(String permissionsIp) { this.permissionsIp = permissionsIp; }
}
