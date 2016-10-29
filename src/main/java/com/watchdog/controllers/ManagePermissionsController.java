package com.watchdog.controllers;

import com.watchdog.business.Permissions;
import com.watchdog.dao.PermissionsDao;
import com.watchdog.dao.UserDao;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
@RequestMapping(value = "/manage_permissions", method = { RequestMethod.GET, RequestMethod.POST })
public class ManagePermissionsController {

    @RequestMapping
    public String listAll(@Valid Permissions permissions, BindingResult bindingResult, Model model) {

        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
        PermissionsDao permissionsDao = ctx.getBean("permissionsDaoImpl", PermissionsDao.class);
        UserDao userDao = ctx.getBean("userDaoImpl", UserDao.class);

        model.addAttribute("permissionsList", permissionsDao.getAll());
        model.addAttribute("userList", userDao.getAll());

        return "/manage_permissions";
    }

    @PostMapping(params = "changePermissionRoleUser")
    public String addNew(@Valid Permissions permissions, Model model) {

        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
        PermissionsDao permissionsDao = ctx.getBean("permissionsDaoImpl", PermissionsDao.class);
        UserDao userDao = ctx.getBean("userDaoImpl", UserDao.class);

        model.addAttribute("permissionsList", permissionsDao.getAll());
        model.addAttribute("userList", userDao.getAll());

        return "/manage_permissions";
    }
}