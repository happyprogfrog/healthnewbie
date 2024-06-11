package me.progfrog.healthnewbie.user.client;

import feign.Response;
import me.progfrog.healthnewbie.user.client.mailgun.SendMailForm;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "mailgun", url = "https://api.mailgun.net/v3/")
@Qualifier("mailgun")
public interface MailgunClient {

    @PostMapping("sandbox18ce11a1f42a441e8183e7c255644a6e.mailgun.org/messages")
    Response sendEmail(@SpringQueryMap SendMailForm form);
}
