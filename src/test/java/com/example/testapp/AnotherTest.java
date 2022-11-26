package com.example.testapp;


import com.example.testapp.controller.MessageController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class AnotherTest {
    @Autowired
    private MessageController controller;
    @Test
    public void contextLoads() throws Exception {
        assertThat(controller).isNotNull();
    }
}
