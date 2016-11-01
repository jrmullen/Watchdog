package com.watchdog.BusinessTests;

import com.watchdog.business.Video;
import com.watchdog.dao.VideoDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertNotNull;

/**
 * Created by Jeremy on 11/1/2016.
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class VideoTests {

    @Mock
    private Video video;
    @Mock
    private VideoDao videoDao;

    @Test
    public void testMockCreation() {
        assertNotNull(video);
        assertNotNull(videoDao);
    }
}
