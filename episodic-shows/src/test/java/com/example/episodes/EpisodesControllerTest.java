package com.example.episodes;

import com.example.MyTestBaseClass;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

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
public class EpisodesControllerTest extends MyTestBaseClass {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    EpisodeRepository episodeRepo;

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
    public void testCreateEpisode() throws Exception {

        long tempCount = episodeRepo.count();

        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, String> userMap = new HashMap<String, String>();
        userMap.put("seasonNumber", "6");
        userMap.put("episodeNumber","6");

        MockHttpServletRequestBuilder request = post("/shows/2/episodes")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userMap));

        this.mockMvc.perform(request).andExpect(status().isOk());




    }

    @Ignore
    @Test
    public void testgetAllEpisodes() throws Exception {

        long tempCount = episodeRepo.count();
        MockHttpServletRequestBuilder request = get("/shows/1/episodes")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);

        this.mockMvc.perform(request).andExpect(status().isOk());




    }


}
