package com.watchdog.controllers.logController;


/**
 * Request to create a new tag for a video
 */
public class CreateVideoTagRequest {
    private String tag;

    public CreateVideoTagRequest() {

    }

    public String getTag() {
        return this.tag;
    }
}
