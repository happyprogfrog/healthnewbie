package me.progfrog.healthnewbie.user.application;

import lombok.RequiredArgsConstructor;
import me.progfrog.healthnewbie.user.client.MailgunClient;
import me.progfrog.healthnewbie.user.client.mailgun.SendMailForm;
import me.progfrog.healthnewbie.user.config.ServerProperties;
import me.progfrog.healthnewbie.user.domain.dto.auth.SignUpForm;
import me.progfrog.healthnewbie.user.domain.model.Customer;
import me.progfrog.healthnewbie.user.exception.CustomException;
import me.progfrog.healthnewbie.user.service.SignUpCustomerService;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

import static me.progfrog.healthnewbie.user.exception.ErrorCode.ALREADY_REGISTER_USER;

@Service
@RequiredArgsConstructor
public class SignUpApplication {

    private final ServerProperties serverProperties;
    private final MailgunClient mailgunClient;
    private final SignUpCustomerService signUpCustomerService;

    private final static int DAYS_TO_ADD = 1;
    private final static String SENDER = "healthnewbie@email.com";
    private final static String SUBJECT = "please verify your account!";

    public void verifyCustomer(String email, String code) {
        signUpCustomerService.verifyEmail(email, code);
    }

    public void signUpCustomer(SignUpForm signUpForm) {
        if (signUpCustomerService.isEmailExist(signUpForm.email())) {
            throw new CustomException(ALREADY_REGISTER_USER);
        } else {
            Customer customer = signUpCustomerService.signUp(signUpForm);
            String verificationCode = getRandomCode();

            SendMailForm sendMailForm = SendMailForm.builder()
                    .from(SENDER)
                    .to(customer.getEmail())
                    .subject(SUBJECT)
                    .text(getVerificationEmailHtmlContent(customer.getEmail(), customer.getNickname(), verificationCode))
                    .build();

            mailgunClient.sendEmail(sendMailForm);
            signUpCustomerService.changeCustomerVerificationInfo(customer.getId(), verificationCode, DAYS_TO_ADD);
        }
    }

    private String getRandomCode() {
        return RandomStringUtils.random(10, true, true);
    }

    private String getVerificationEmailHtmlContent(String email, String nickname, String code) {
        return """
                Hello, %s! Please Copy&Paste the link below for verification.
                %s?email=%s&code=%s
                """.formatted(nickname, getUrl(), email, code);
    }

    private String getUrl() {
        // http://localhost:8081/signup/verify/customer
        return serverProperties.getUrl() + ":" + serverProperties.getPort() + "/signup/verify/customer";
    }
}
