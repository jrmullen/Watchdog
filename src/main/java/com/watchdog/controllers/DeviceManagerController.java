package com.watchdog.controllers;


import com.watchdog.business.Device;
import com.watchdog.dao.DeviceDao;
import com.watchdog.dao.DeviceDaoImpl;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class DeviceManagerController {

    @GetMapping(value = "device_manager")
    public String device(Device device) {
        return "device_manager";
    }

    @PostMapping(value = "/device_manager")
    public String addNewPost(@Valid Device device, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "user_home";
        }

        //Initialize database and create DeviceDao object
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
        DeviceDao deviceDao = ctx.getBean("deviceDaoImpl", DeviceDao.class); //first parameter is the id found in the spring.xml file

        model.addAttribute("deviceName", device.getDeviceName());
        model.addAttribute("deviceMac", device.getDeviceMac());
        model.addAttribute("deviceIp", device.getDeviceIp());


        //Save device to DB
        deviceDao.save(device);
        model.addAttribute("deviceList", deviceDao.getAll());

        //redirect to device manager page
        return "/device_manager";
    }
}