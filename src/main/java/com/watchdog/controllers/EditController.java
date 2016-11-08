package com.watchdog.controllers;

import com.watchdog.business.User;
import com.watchdog.dao.device.DeviceDao;
import com.watchdog.dao.user.UserDao;
import com.watchdog.services.UserUpdateService;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

/**
 * Created by Richard on 10/27/2016.
 */

@Controller
public class EditController {

    ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
    UserDao userDao = ctx.getBean("userDaoImpl", UserDao.class);
    DeviceDao deviceDao = ctx.getBean("deviceDaoImpl", DeviceDao.class);
    
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    String userEmail = auth.getName();

    @GetMapping(value = "/edit")
    public String edit(User user, Model model) {

        int userId = userDao.getByEmail(userEmail).getId();
        model.addAttribute("user", userDao.getById(userId));

        return "edit";
    }

    @PostMapping(value = "/edit")
    public String addNewPost(@Valid User user, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "edit";
        }

        int userId = userDao.getByEmail(userEmail).getId();

        model.addAttribute("firstName", user.getFirstName());
        model.addAttribute("lastName", user.getLastName());
        model.addAttribute("password", user.getPassword());
        model.addAttribute("passwordConfirm", user.getPasswordConfirm());
        user.setId(userId);
        user.setEmail(userEmail);
        UserUpdateService.updateUser(user);

        model.addAttribute("deviceList", deviceDao.getAll());

        return "user_home";
    }
}
