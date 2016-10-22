package com.watchdog.controllers;

/**
 * Created by Jeremy on 10/20/2016.
 */

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WebcamStreamTestController {

    @RequestMapping(value = "/stream_test")
    String stream_test() {
        return "stream_test";
    }

}
