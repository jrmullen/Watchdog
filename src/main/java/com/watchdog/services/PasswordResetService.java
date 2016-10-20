package com.watchdog.services;

import com.watchdog.business.User;
import com.watchdog.dao.UserDao;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import java.util.Random;

/**
 * Created by Richard on 10/18/2016.
 */
public class PasswordResetService {

    public static void resetPass(User user) {
        //Initialize database and create UserDao object
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
        UserDao userDao = ctx.getBean("userDaoImpl", UserDao.class); //first parameter is the id found in the spring.xml file
        User user2 = userDao.getByEmail(user.getEmail());
        user2.setEmail(user.getEmail());
        Random rnd = new Random();
        String pass = generateString(rnd, "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789", 8);
        user2.setPassword(pass.toCharArray());
        user2.setEncodedPassword(pass.toCharArray());
        userDao.update(user2);
        Sendmail(pass, user.getEmail());
    }

    public static String generateString(Random rng, String characters, int length) {
        char[] text = new char[length];
        for (int i = 0; i < length; i++) {
            text[i] = characters.charAt(rng.nextInt(characters.length()));
        }
        return new String(text);
    }

    public static void Sendmail(String pass, String email) {
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
            message.setSubject("New Password PasswordResetService");
            message.setText("User, your password has been reset to "
                    + pass + " if this was not done by you please notify us as soon as possible.");

            Transport.send(message);

            System.out.println("Done");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
