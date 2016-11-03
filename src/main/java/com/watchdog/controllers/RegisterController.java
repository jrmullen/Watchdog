package com.watchdog.controllers;


import com.watchdog.business.User;
import com.watchdog.dao.user.UserDao;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.SQLException;

@Controller
public class RegisterController {

    @GetMapping(value = "/register")
    public String register(User user) {
        return "register";
    }

    @PostMapping(value = "/register")
    public String addNewPost(@Valid User user, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "register";
        }

        //Initialize database and create UserDao object
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
        UserDao userDao = ctx.getBean("userDaoImpl", UserDao.class); //first parameter is the id found in the spring.xml file

        user.setEncodedPassword(user.getPassword());
        model.addAttribute("firstName", user.getFirstName());
        model.addAttribute("lastName", user.getLastName());
        model.addAttribute("email", user.getEmail());
        model.addAttribute("password", user.getPassword());
        model.addAttribute("passwordConfirm", user.getPasswordConfirm());

        if (userDao.emailAlreadyExists(user.getEmail())) {
            String duplicateEmail = "This email already exists for an account. " +
                                "Please register with a different email address.";
            model.addAttribute("duplicateEmail", duplicateEmail);

            return "register";
        }
        userDao.save(user);

        //redirect to login page
        return "login";
    }
}
