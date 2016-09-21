package com.watchdog;

import com.watchdog.business.User;
import com.watchdog.dao.UserDao;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);

        //Get the Spring Context
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");

        //To use JdbcTemplate
        UserDao userDao = ctx.getBean("userDaoImpl", UserDao.class); //first parameter is the id found in the spring.xml file

        //Run some tests for JDBC CRUD operations
//        User user = new User();
//        user.setId(8);
//        user.setPermissionId(4);
//        user.setFirstName("name");
//        user.setLastName("lastName");
//        user.setEmail("test@email.com");
//        user.setPassword("taco1234");

        //Create
//        userDao.save(user);

        //Read
        User user1 = userDao.getById(7);
        System.out.println("Employee Retrieved:" + user1);

        //Update
//        user.setFirstName("John");
//        userDao.update(user);

        //Get All
//        List<User> userList = userDao.getAll();
//        System.out.println(userList);

        //Delete
//        userDao.deleteById(7);

        //Close Spring Context
        ctx.close();

        System.out.println("DONE");
    }

}
