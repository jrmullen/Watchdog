package com.watchdog.controllers;


import com.watchdog.business.User;
import com.watchdog.dao.UserDao;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Random;


import javax.validation.Valid;

@Controller
public class ResetController {


    @GetMapping(value ="/reset")
    public String reset(User user) {
        return "reset";
    }
    @RequestMapping("/password")
    public String password(User user){
        return "password";
    }

    @PostMapping(value = "/reset")
    public String addNewPost(@Valid User user, BindingResult bindingResult, Model model) throws Exception {
        if (bindingResult.hasErrors())
        {
            return "reset";
        }
        model.addAttribute("email", user.getEmail());
        //Initialize database and create UserDao object
        //ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
        //UserDao userDao = ctx.getBean("userDaoImpl", UserDao.class); //first parameter is the id found in the spring.xml file
        Random rnd = new Random();
        String pass = generateString(rnd, "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789",9);
        user.setPassword(pass);
        user.setPasswordConfirm(pass);
        return "/password";
    }

    public static String generateString(Random rng, String characters, int length)
    {
        char[] text = new char[length];
        for (int i = 0; i < length; i++)
        {
            text[i] = characters.charAt(rng.nextInt(characters.length()));
        }
        return new String(text);
    }
}

