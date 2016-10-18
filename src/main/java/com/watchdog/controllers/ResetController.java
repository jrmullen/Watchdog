package com.watchdog.controllers;


import com.watchdog.business.Reset;
import com.watchdog.business.User;
import com.watchdog.dao.UserDao;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


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
        Reset.resetPass(user);
        return "/password";
    }

}

