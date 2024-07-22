package com.example.bankapp;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest
@ContextConfiguration(classes = {BankappApplication.class, TestSecurityConfig.class})
public class BankappApplicationTests {

    @Test
    public void contextLoads() {
    }

}
