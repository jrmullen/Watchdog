package com.watchdog.controllers;

import com.watchdog.dao.DeviceDao;
import com.watchdog.dao.UserDao;
import com.watchdog.services.UserUpdateService;
import com.watchdog.business.User;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

/**
 * Created by Richard on 10/27/2016.
 */
@Controller
public class EditController {

    @GetMapping(value = "/edit")
    public String register(User user, Model model) {
        //Initialize database and create Dao object
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
        DeviceDao deviceDao = ctx.getBean("deviceDaoImpl", DeviceDao.class); //first parameter is the id found in the spring.xml file
        UserDao userDao = ctx.getBean("userDaoImpl", UserDao.class);

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = auth.getName(); //get logged in username

        int userId = userDao.getByEmail(userEmail).getId();
        model.addAttribute("user", userDao.getById(userId));
        return "edit";
    }

    @PostMapping(value = "/edit")
    public String addNewPost(@Valid User user, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "edit";
        }
        //Initialize database and create Dao object
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
        DeviceDao deviceDao = ctx.getBean("deviceDaoImpl", DeviceDao.class); //first parameter is the id found in the spring.xml file
        UserDao userDao = ctx.getBean("userDaoImpl", UserDao.class);

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = auth.getName(); //get logged in username
        int userId = userDao.getByEmail(userEmail).getId();

        model.addAttribute("firstName", user.getFirstName());
        model.addAttribute("lastName", user.getLastName());
        model.addAttribute("password", user.getPassword());
        model.addAttribute("passwordConfirm", user.getPasswordConfirm());
        user.setId(userId);
        user.setEmail(userEmail);
        UserUpdateService.updateUser(user);

        return "user_home";
    }
}
