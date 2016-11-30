package com.watchdog.controllers.logController;

/**
 * Created by Jeremy Mullen on 11/28/2016.
 */

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
