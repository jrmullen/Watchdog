package com.watchdog.business;

import javax.validation.constraints.NotNull;

/**
 * Created by BBuck on 10/4/16.
 */
public class Permissions {

    private int id;

    @NotNull
    private String role,
            description;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}


