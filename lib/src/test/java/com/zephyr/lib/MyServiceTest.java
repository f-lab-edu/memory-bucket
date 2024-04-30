package com.zephyr.lib;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest("service.message=Hello")
public class MyServiceTest {

    @Autowired
    private MyService myService;

    @Test
    public void contextLoads() {
        assertEquals(myService.message(),"Hello");
    }

    @SpringBootApplication
    static class TestConfiguration {
    }

}