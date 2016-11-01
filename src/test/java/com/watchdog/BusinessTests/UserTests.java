package com.watchdog.BusinessTests;

import com.watchdog.business.User;
import com.watchdog.dao.UserDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertNotNull;

/**
 * Created by jmullen on 9/26/16.
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserTests {

    @Mock
    private User user;
    @Mock
    private UserDao userDao;

    @Test
    public void testMockCreation() {
        assertNotNull(user);
        assertNotNull(userDao);
    }
}
