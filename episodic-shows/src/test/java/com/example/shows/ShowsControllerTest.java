package com.example.shows;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by trainer9 on 5/17/17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ShowsControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ShowRepository showRepo;

    @Before
    public void setup() {
        //showRepo.deleteAll();
    }

    @After
    public void tearUp()
    {
       // showRepo.deleteAll();
    }


    @Test
    @Transactional
    @Rollback
    public void testCreateShow() throws Exception {

        long tempCount = showRepo.count();

        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, String> userMap = new HashMap<String, String>();
        userMap.put("name", "DishShows");

        MockHttpServletRequestBuilder request = post("/shows")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userMap));

        this.mockMvc.perform(request).andExpect(status().isOk());




    }

    @Test
    public void testgetAllShows() throws Exception {

        long tempCount = showRepo.count();
        MockHttpServletRequestBuilder request = get("/shows")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);

        this.mockMvc.perform(request).andExpect(status().isOk());




    }


}
