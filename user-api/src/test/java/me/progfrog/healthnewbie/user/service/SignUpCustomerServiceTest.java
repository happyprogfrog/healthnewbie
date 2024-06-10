package me.progfrog.healthnewbie.user.service;

import me.progfrog.healthnewbie.user.domain.dto.auth.SignUpForm;
import me.progfrog.healthnewbie.user.domain.model.Customer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class SignUpCustomerServiceTest {

    @Autowired
    private SignUpCustomerService signUpCustomerService;

    @Test
    void signUp() {
        String email = "a@a.com";
        String nickname = "frog";
        String password = "1234$";
        SignUpForm signUpForm = new SignUpForm(email, nickname, password);
        Customer customer = signUpCustomerService.signUp(signUpForm);
        assertThat(customer.getEmail()).isEqualTo(email);
        assertThat(customer.getNickname()).isEqualTo(nickname);
    }
}