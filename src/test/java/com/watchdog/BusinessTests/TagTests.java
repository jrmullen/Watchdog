package com.watchdog.BusinessTests;

import com.watchdog.business.Tag;
import com.watchdog.dao.TagDao;
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
public class TagTests {

    @Mock
    private Tag tag;
    @Mock
    private TagDao tagDao;

    @Test
    public void testMockCreation() {
        assertNotNull(tag);
        assertNotNull(tagDao);
    }
}
