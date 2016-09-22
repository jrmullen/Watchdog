package com.watchdog;


import com.watchdog.business.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class LoginController {

    @RequestMapping("/login")
    public void login(){
    }

    @GetMapping(value = "/login")
    public String login(User user) {
        return "login";
    }

    @PostMapping(value = "/login")
    public String addNewPost(@Valid User user, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "login";
        }
        model.addAttribute("email", user.getEmail());
        model.addAttribute("password", user.getPassword());
        return "/user_home";
    }
}
