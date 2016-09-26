package com.watchdog;

import com.watchdog.business.User;
import com.watchdog.dao.UserDao;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by jmullen on 9/26/16.
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserTests {

//        @Autowired
//        private MockMvc mockMvc;

//    private User user;
//    private UserDao userDao;

//    @Before
//    public void setupMock() {
//        user = mock(User.class);
//        userDao = mock(UserDao.class);
//    }

    @Mock
    private User user;
    @Mock
    private UserDao userDao;

    @Test
    public void testMockCreation() {
        assertNotNull(user);
        assertNotNull(userDao);
    }



//    @Test
//    public void homePage() throws Exception {
//        // N.B. jsoup can be useful for asserting HTML content
//        mockMvc.perform(get("/index.html"))
//                .andExpect(content().string(containsString("Get your greeting")));
//    }
}
