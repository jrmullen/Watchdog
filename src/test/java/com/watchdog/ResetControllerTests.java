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
 * Created by Jeremy on 11/1/2016.
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ResetControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testDeviceManagerControllerStatus() throws Exception {
        mockMvc.perform(get("/password.html")).andExpect(status().isOk());
        mockMvc.perform(get("/reset.html")).andExpect(status().isOk());
    }
}
