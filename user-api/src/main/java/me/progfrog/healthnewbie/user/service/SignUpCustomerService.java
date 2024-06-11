package me.progfrog.healthnewbie.user.service;

import lombok.RequiredArgsConstructor;
import me.progfrog.healthnewbie.user.domain.dto.auth.SignUpForm;
import me.progfrog.healthnewbie.user.domain.model.Customer;
import me.progfrog.healthnewbie.user.domain.repository.CustomerRepository;
import me.progfrog.healthnewbie.user.exception.CustomException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Locale;
import java.util.Optional;

import static me.progfrog.healthnewbie.user.exception.ErrorCode.*;

@Service
@RequiredArgsConstructor
public class SignUpCustomerService {

    private final BCryptPasswordEncoder encoder;
    private final CustomerRepository customerRepository;

    public Customer signUp(SignUpForm signUpForm) {
        return customerRepository.save(signUpForm.toEntity(signUpForm, encoder));
    }

    public boolean isEmailExist(String email) {
        return customerRepository.findByEmail(email.toLowerCase(Locale.ROOT))
                .isPresent();
    }

    @Transactional
    public void verifyEmail(String email, String code) {
        Customer customer = customerRepository.findByEmail(email.toLowerCase(Locale.ROOT))
                .orElseThrow(() -> new CustomException(NOT_FOUND_USER));

        if (customer.isVerify()) {
            throw new CustomException(ALREADY_VERIFY);
        } else if (!customer.getVerificationCode().equals(code)) {
            throw new CustomException(WRONG_VERIFICATION);
        } else if (customer.getVerifyExpiredAt().isBefore(LocalDateTime.now())) {
            throw new CustomException(EXPIRE_CODE);
        }

        customer.updateVerify(true);
    }

    @Transactional
    public void changeCustomerVerificationInfo(Long customerId, String verificationCode, int daysToAdd) {
        Optional<Customer> customerOptional = customerRepository.findById(customerId);
        if (customerOptional.isEmpty()) {
            throw new CustomException(NOT_FOUND_USER);
        }

        Customer customer = customerOptional.get();
        customer.updateVerificationInfo(verificationCode, LocalDateTime.now().plusDays(daysToAdd));
    }
}
