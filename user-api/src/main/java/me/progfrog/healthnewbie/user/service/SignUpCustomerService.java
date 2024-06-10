package me.progfrog.healthnewbie.user.service;

import lombok.RequiredArgsConstructor;
import me.progfrog.healthnewbie.user.domain.dto.auth.SignUpForm;
import me.progfrog.healthnewbie.user.domain.model.Customer;
import me.progfrog.healthnewbie.user.domain.repository.CustomerRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SignUpCustomerService {

    private final BCryptPasswordEncoder encoder;
    private final CustomerRepository customerRepository;

    public Customer signUp(SignUpForm signUpForm) {
        return customerRepository.save(signUpForm.toEntity(signUpForm, encoder));
    }
}
