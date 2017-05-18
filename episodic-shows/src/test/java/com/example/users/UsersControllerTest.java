package com.example.users;

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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by trainer9 on 5/17/17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UsersControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    UserRepository userRepo;

    @Before
    public void setup() {
        //userRepo.deleteAll();
    }

    @After
    public void tearUp()
    {
       // userRepo.deleteAll();
    }


    @Test
    @Transactional
    @Rollback
    public void testCreateUser() throws Exception {

        long tempCount = userRepo.count();

        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, String> userMap = new HashMap<String, String>();
        userMap.put("email", "aravind.kuppala@dish.com");

        MockHttpServletRequestBuilder request = post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userMap));

        this.mockMvc.perform(request).andExpect(status().isOk());




    }


}