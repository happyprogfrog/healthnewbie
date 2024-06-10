package me.progfrog.healthnewbie.user.service;

import feign.Response;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class EmailSendServiceTest {

    @Autowired
    private EmailSendService emailSendService;

    @Test
    void sendEmail() {
        Response response = emailSendService.sendEmail();
        System.out.println(response);
    }
}