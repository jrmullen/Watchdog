package com.watchdog.controllers;


import com.watchdog.business.User;
import com.watchdog.dao.UserDao;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.io.UnsupportedEncodingException;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

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
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
        UserDao userDao = ctx.getBean("userDaoImpl", UserDao.class); //first parameter is the id found in the spring.xml file
        User user2 = userDao.getByEmail(user.getEmail());
        user2.setEmail(user.getEmail());
        Random rnd = new Random();
        String pass = generateString(rnd, "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789",8);
        user2.setPassword(pass.toCharArray());
        user2.setEncodedPassword(pass.toCharArray());
        userDao.update(user2);
        Sendmail(pass, user.getEmail());
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

    public static void Sendmail(String pass, String email)
    {
    final String username = "watchdogprojectcse480@gmail.com";
    final String password = "Watchdog480";

    Properties props = new Properties();
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "25");

    Session session = Session.getInstance(props,
            new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, password);
                }
            });

        try {

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress("watchdogprojectcse480@gmail.com"));
        message.setRecipients(Message.RecipientType.TO,
                InternetAddress.parse(email));
        message.setSubject("New Password Reset");
        message.setText("User, your password has been reset to "
                + pass +" if this was not done by you please notify us as soon as possible.");

        Transport.send(message);

        System.out.println("Done");

    } catch (MessagingException e) {
        throw new RuntimeException(e);
    }
}
}

