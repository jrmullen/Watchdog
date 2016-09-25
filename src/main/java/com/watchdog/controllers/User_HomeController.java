package com.watchdog.controllers;

        import org.springframework.stereotype.Controller;
        import org.springframework.ui.Model;
        import org.springframework.web.bind.annotation.RequestMapping;
        import org.springframework.web.bind.annotation.RequestMethod;
        import org.springframework.web.bind.annotation.RequestParam;
        import java.security.Principal;



@Controller
public class User_HomeController {

    @RequestMapping("/user_home")
    public void userHome(){
    }

    @RequestMapping(value = "/user_home", method = RequestMethod.GET)
    public String index(Principal principal) {
        return principal != null ? "user_home" : "user_home";
    }

}