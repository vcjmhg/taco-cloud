package com.vcjmhg.tacocloud.controller;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import static org.hamcrest.core.StringContains.containsString;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

/**
 * @author vcjmhg
 * @version TODO
 * @description TODO
 * @date 2020/6/27 16:35
 **/
@RunWith(SpringRunner.class)
@WebMvcTest   // <1>
public class HomeControllerTest {

    @Autowired
    private MockMvc mockMvc;   // <2>

    @Test
    public void testHomePage() throws Exception {
        mockMvc.perform(get("/"))    // <3>

                .andExpect(status().isOk())  // <4>

                .andExpect(view().name("home"))  // <5>

                .andExpect((ResultMatcher) content().string(           // <6>
                        containsString("Welcome to...")));
    }

}
