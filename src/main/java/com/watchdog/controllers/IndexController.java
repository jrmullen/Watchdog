package com.watchdog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by theNextThing on 10/23/2016.
 */

@Controller
public class IndexController {

    @RequestMapping(value={"/index", "/"})
    public String index() {

        return "index";
    }
}
