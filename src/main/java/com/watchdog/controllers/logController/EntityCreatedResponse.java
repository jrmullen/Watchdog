package com.watchdog.controllers.logController;

/**
 * Created by Jeremy Mullen on 11/28/2016.
 */

/**
 * Response to a creation of an entity
 */
public class EntityCreatedResponse {
    private int id;

    public EntityCreatedResponse(final int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
