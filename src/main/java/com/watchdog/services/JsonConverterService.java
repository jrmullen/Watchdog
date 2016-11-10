package com.watchdog.services;

import com.google.gson.Gson;

/**
 * Created by Jeremy on 11/9/2016.
 */
public class JsonConverterService {

    Gson gson = new Gson();

    public String objectToJson(Object object) {
        return gson.toJson(object);
    }
}
