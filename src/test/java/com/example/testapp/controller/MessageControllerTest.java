package com.example.testapp.controller;

import com.example.testapp.repository.UserRepository;
import com.example.testapp.security.CustomUserDetailsService;
import com.example.testapp.security.JWTAuthenticationEntryPoint;
import com.example.testapp.security.JWTTokenProvider;
import com.example.testapp.service.MessageService;
import com.example.testapp.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class MessageControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private UserService userService;
    @MockBean
    private MessageService messageService;
    @MockBean
    private JWTAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    @MockBean
    private CustomUserDetailsService customUserDetailsService;
    @MockBean
    private JWTTokenProvider jwtTokenProvider;
    @MockBean
    private UserRepository userRepository;

    @Test
    void getMessages() throws Exception {
        this.mockMvc.perform(get("/api/v1/messages/"))
                .andDo(print()).andExpect(status().isOk());
    }
}