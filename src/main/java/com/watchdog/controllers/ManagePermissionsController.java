package com.watchdog.controllers;

import com.watchdog.business.Permissions;
import com.watchdog.business.User;
import com.watchdog.dao.permissions.PermissionsDao;
import com.watchdog.dao.user.UserDao;
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
@RequestMapping(value = "/manage_permissions", method = {RequestMethod.GET, RequestMethod.POST})
public class ManagePermissionsController {

    ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
    PermissionsDao permissionsDao = ctx.getBean("permissionsDaoImpl", PermissionsDao.class);
    UserDao userDao = ctx.getBean("userDaoImpl", UserDao.class);

    @RequestMapping
    public String listAll(@Valid Permissions permissions, BindingResult bindingResult, Model model) {

        model.addAttribute("permissionsList", permissionsDao.getAll());
        model.addAttribute("userList", userDao.getAll());

        return "/manage_permissions";
    }

    @PostMapping(params = "changePermissionRoleUser")
    public String addNew(@RequestParam int id, @RequestParam int permissionId,
                         Permissions permissions, User user, Model model) {

        model.addAttribute("id", user.getId());
        model.addAttribute("permissionId", user.getPermissionId());
        userDao.updatePermission(user);

        model.addAttribute("permissionsList", permissionsDao.getAll());
        model.addAttribute("userList", userDao.getAll());

        return "/manage_permissions";
    }
}