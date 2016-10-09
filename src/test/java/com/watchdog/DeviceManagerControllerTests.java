package com.watchdog;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by BBuck on 10/6/16.
 */

@RunWith (SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc

public class DeviceManagerControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testDeviceManagerControllerStatus() throws Exception {
        mockMvc.perform(get("/device_manager.html")).andExpect(status().isOk());
    }
}
