package me.progfrog.healthnewbie.user.service;

import feign.Response;
import lombok.RequiredArgsConstructor;
import me.progfrog.healthnewbie.user.client.MailgunClient;
import me.progfrog.healthnewbie.user.client.mailgun.SendMailForm;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailSendService {

    private final MailgunClient mailgunClient;

    public Response sendEmail() {

        SendMailForm form = SendMailForm.builder().
                from("healthnewbie@email.com")
                .to("happyprogfrog@gmail.com")
                .subject("Test email from healthnewbie")
                .text("Hello, World!").build();
        return mailgunClient.sendEmail(form);
    }
}
