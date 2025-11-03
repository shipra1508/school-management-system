package com.school.authservice.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class SignoutControllerTest {

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        SignoutController signoutController = new SignoutController();
        mockMvc = MockMvcBuilders.standaloneSetup(signoutController).build();
    }

    @Test
    public void testSignoutSuccess() throws Exception {
        mockMvc.perform(post("/auth/signout")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("Signed out successfully !!"));
    }
}
