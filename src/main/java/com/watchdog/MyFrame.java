package com.watchdog;

import java.awt.EventQueue;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

// Imports required from Application.java
import com.watchdog.business.User;
import com.watchdog.dao.UserDao;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyFrame extends JFrame {
    private JPanel contentPane;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        /**
        // Start code from Application.java
         **/
        SpringApplication.run(Application.class, args);

        //Get the Spring Context
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");

        //To use JdbcTemplate
        UserDao userDao = ctx.getBean("userDaoImpl", UserDao.class); //first parameter is the id found in the spring.xml file

        //Run some tests for JDBC CRUD operations
//        User user = new User();
//        user.setFirstName("boringFirstName");
//        user.setLastName("boringLastName");
//        user.setEmail("generic@email.com");

//        PasswordService ps = new PasswordService();
//        System.out.println(ps.encrypt("password123"));

//        user.setPassword("password5435234");

//        Create
//        userDao.save(user);

        //Read
//        User user1 = userDao.getById(3);
//        System.out.println("User Retrieved:" + user1);

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

        /**
         * End code from Application.java
         **/


        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    MyFrame frame = new MyFrame();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public MyFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 650, 490);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        new MyThread().start();
    }

    //com.watchdog.VideoCap videoCap = new com.watchdog.VideoCap();

    public void paint(Graphics g){
        g = contentPane.getGraphics();
        //g.drawImage(videoCap.getOneFrame(), 0, 0, this);
    }

    class MyThread extends Thread{
        @Override
        public void run() {
            for (;;){
                repaint();
                try { Thread.sleep(30);
                } catch (InterruptedException e) {    }
            }
        }
    }
}