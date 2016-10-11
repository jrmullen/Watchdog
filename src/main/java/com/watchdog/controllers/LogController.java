package com.watchdog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LogController {

    @RequestMapping("/logview")
    public void logview(){
    }
}
