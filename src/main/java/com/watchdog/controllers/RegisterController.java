package com.watchdog.controllers;


import com.watchdog.business.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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
        model.addAttribute("firstName", user.getFirstName());
        model.addAttribute("lastName", user.getLastName());
        model.addAttribute("email", user.getEmail());
        model.addAttribute("password", user.getPassword());
        model.addAttribute("passwordConfirm", user.getPasswordConfirm());
        return "/result";
    }
}
