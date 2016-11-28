package com.watchdog.controllers.logController;

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
