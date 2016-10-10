package com.watchdog;

import com.watchdog.business.Device;
import com.watchdog.dao.DeviceDao;
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
public class DeviceTests {

    @Mock
    private Device device;
    @Mock
    private DeviceDao deviceDao;

    @Test
    public void testMockCreation() {
        assertNotNull(device);
        assertNotNull(deviceDao);
    }
}
