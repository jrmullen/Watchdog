package com.watchdog.controllers;

import com.watchdog.business.Device;
import com.watchdog.dao.DeviceDao;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
@RequestMapping(value = "/manage_permissions", method = { RequestMethod.GET, RequestMethod.POST })
public class ManagePermissionsController {

    @RequestMapping
    public String listAll(@Valid Device permissions, BindingResult bindingResult, Model model) {

        //Initialize database and create DeviceDao object
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
        DeviceDao permissionsDao = ctx.getBean("permissionsDaoImpl", DeviceDao.class); //first parameter is the id found in the spring.xml file

        model.addAttribute("permissionsList", permissionsDao.getAll());

        //redirect to permissions manager page
        return "/manage_permissions";
    }

    @PostMapping(params = "addDevice")
    public String addNew(@Valid Device permissions, Model model) {

        //Initialize database and create DeviceDao object
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
        DeviceDao permissionsDao = ctx.getBean("permissionsDaoImpl", DeviceDao.class); //first parameter is the id found in the spring.xml file

        //Save permissions to DB
        if(!permissions.getDeviceName().equals("") && !permissions.getDeviceMac().equals("")
                && !permissions.getDeviceIp().equals("")) {

            model.addAttribute("permissionsName", permissions.getDeviceName());
            model.addAttribute("permissionsMac", permissions.getDeviceMac());
            model.addAttribute("permissionsIp", permissions.getDeviceIp());
            permissionsDao.save(permissions);
        }
        model.addAttribute("permissionsList", permissionsDao.getAll());

        //redirect to permissions manager page
        return "/manage_permissions";
    }


    //delete permissions
    @RequestMapping(params = "deleteDevice")
    public String delete (@RequestParam int id, Device permissions, Model model ) {

        //Initialize database and create DeviceDao object
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
        DeviceDao permissionsDao = ctx.getBean("permissionsDaoImpl", DeviceDao.class); //first parameter is the id found in the spring.xml file

        permissionsDao.deleteById(id);
        model.addAttribute("permissionsList", permissionsDao.getAll());

        return "/manage_permissions";
    }
}