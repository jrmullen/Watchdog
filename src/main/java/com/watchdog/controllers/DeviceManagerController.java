package com.watchdog.controllers;

import com.watchdog.business.Device;
import com.watchdog.dao.device.DeviceDao;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.regex.Pattern;

@Controller
@RequestMapping(value = "/device_manager", method = {RequestMethod.GET, RequestMethod.POST})
public class DeviceManagerController {

    //Initialize database and create DeviceDao object
    ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
    DeviceDao deviceDao = ctx.getBean("deviceDaoImpl", DeviceDao.class); //first parameter is the id found in the spring.xml file

    @RequestMapping
    public String listAll(@Valid Device device, BindingResult bindingResult, Model model) {

        model.addAttribute("deviceList", deviceDao.getAll());

        //redirect to device manager page
        return "/device_manager";
    }

    @PostMapping(params = "addDevice")
    public String addNew(@Valid Device device, Model model) {

        model.addAttribute("deviceName", device.getDeviceName());
        model.addAttribute("deviceMac", device.getDeviceMac());
        model.addAttribute("deviceIp", device.getDeviceAddress());

        //Save device to DB
        if (!device.getDeviceName().equals("") && !device.getDeviceMac().equals("")
                && !device.getDeviceAddress().equals("")) {

            if (!isValidMacAddress(device.getDeviceMac())){
                model.addAttribute("errorMessage", "Invalid MAC address");
            } else if (deviceDao.checkMacExists(device.getDeviceMac())) {
                model.addAttribute("errorMessage", "A device with this MAC address already exists." +
                        " Please enter a unqiue MAC address.");
            }
            else if (!isValidPort(device.getDevicePort()) || !device.getDevicePort().equals(device.getDevicePort())) {
                model.addAttribute("errorMessage", "Port field must be a number between 1 and 65,535.");
            }
            else if (deviceDao.checkDeviceNameExists(device.getDeviceName())) {
                model.addAttribute("errorMessage", "A device with this name already exists." +
                        " Please enter a unqiue device name.");
            }
            else {
                deviceDao.save(device);
                model.addAttribute("successMessage", "Device successfully saved.");
            }

        } else if (device.getDeviceName().equals("") || device.getDeviceMac().equals("")
                || device.getDeviceAddress().equals("")) {
            model.addAttribute("errorMessage", "Please fill in all required fields.");

        }

        model.addAttribute("deviceList", deviceDao.getAll());

        //redirect to device manager page
        return "/device_manager";
    }


    //delete device
    @RequestMapping(params = "deleteDevice")
    public String delete(@RequestParam int id, Device device, Model model) {

        deviceDao.deleteById(id);
        model.addAttribute("deviceList", deviceDao.getAll());

        return "/device_manager";
    }

    private boolean isValidMacAddress(String mac) {
        final Pattern pattern = Pattern.compile("(([0-9A-Fa-f]{2}[-:]){5}[0-9A-Fa-f]{2})|(([0-9A-Fa-f]{4}.){2}[0-9A-Fa-f]{4})");
        return pattern.matcher(mac).matches();
    }

    private boolean isValidPort(String port) {

        if (port.matches("[0-9]+") && port.length() < 6) {
            int checkRange = Integer.parseInt(port);

            if (checkRange  < 1 | checkRange > 65535) {
                return false;
            }
            return true;
        }
        return false;
    }

}