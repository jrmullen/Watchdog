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
@RequestMapping(value = "/device_manager", method = { RequestMethod.GET, RequestMethod.POST })
public class DeviceManagerController {

    @RequestMapping
    public String listAll(@Valid Device device, BindingResult bindingResult, Model model) {

        //Initialize database and create DeviceDao object
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
        DeviceDao deviceDao = ctx.getBean("deviceDaoImpl", DeviceDao.class); //first parameter is the id found in the spring.xml file

        model.addAttribute("deviceList", deviceDao.getAll());

        //redirect to device manager page
        return "/device_manager";
    }

    @PostMapping(params = "addDevice")
    public String addNew(@Valid Device device, Model model) {

        //Initialize database and create DeviceDao object
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
        DeviceDao deviceDao = ctx.getBean("deviceDaoImpl", DeviceDao.class); //first parameter is the id found in the spring.xml file

        //Save device to DB
        if(!device.getDeviceName().equals("") && !device.getDeviceMac().equals("")
                                            && !device.getDeviceIp().equals("")) {

            model.addAttribute("deviceName", device.getDeviceName());
            model.addAttribute("deviceMac", device.getDeviceMac());
            model.addAttribute("deviceIp", device.getDeviceIp());
            deviceDao.save(device);
        }
        model.addAttribute("deviceList", deviceDao.getAll());

        //redirect to device manager page
        return "/device_manager";
    }


    //delete device
    @RequestMapping(params = "deleteDevice")
    public String delete (@RequestParam int id, Device device, Model model ) {

        //Initialize database and create DeviceDao object
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
        DeviceDao deviceDao = ctx.getBean("deviceDaoImpl", DeviceDao.class); //first parameter is the id found in the spring.xml file

        deviceDao.deleteById(id);
        model.addAttribute("deviceList", deviceDao.getAll());

        return "/logview";
    }
}