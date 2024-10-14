package com.example.bookhub;

import com.example.bookhub.email.TestEmailConfig;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

@ActiveProfiles("test")
@SpringBootTest
@ContextConfiguration(classes = {TestEmailConfig.class})
class BookhubApplicationTests {

    @Test
    void contextLoads() {
    }

}
