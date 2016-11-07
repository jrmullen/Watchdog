package com.watchdog.controllers;

import com.watchdog.business.Device;
import com.watchdog.dao.device.DeviceDao;
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

        String errorMessage = null;
        String successMessage = null;

        model.addAttribute("deviceName", device.getDeviceName());
        model.addAttribute("deviceMac", device.getDeviceMac());
        model.addAttribute("deviceIp", device.getDeviceIp());

        //Save device to DB
        if(!device.getDeviceName().equals("") && !device.getDeviceMac().equals("")
                && !device.getDeviceIp().equals("")) {

            String testNumeric = "";
            model.addAttribute("devicePort", testNumeric);

           /* if(isNumeric(testNumeric)) {
                model.addAttribute("devicePort", device.getDevicePort());*/

                if (deviceDao.checkMacExists(device.getDeviceMac())) {
                    model.addAttribute("errorMessage", "A device with this MAC address already exists." +
                            " Please enter a unqiue MAC address.");
                }
                else if (device.getDevicePort() < 1 | device.getDevicePort() > 65535) {
                    model.addAttribute("errorMessage", "Port field must be left blank or be number between 1 and 65,535.");
                }
                else {
                    deviceDao.save(device);
                    model.addAttribute("successMessage", "Device successfully saved.");
                }
          /*  }
            else {
                model.addAttribute("errorMessage", "Port field must be a number.");
            }*/

        }
        else if (device.getDeviceName().equals("") || device.getDeviceMac().equals("")
                || device.getDeviceIp().equals("")) {
            model.addAttribute("errorMessage", "Please fill in all required fields.");

        }
        errorMessage = null;
        successMessage = null;

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

        return "/device_manager";
    }

    public static boolean isNumeric(String str)
    {
        return str.matches("-?\\d+(\\.\\d+)?");  //match a number with optional '-' and decimal.
    }
}