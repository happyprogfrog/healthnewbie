package me.progfrog.healthnewbie.user.domain.dto.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import me.progfrog.healthnewbie.user.domain.model.Customer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public record SignUpForm(
        @NotBlank
        @Email
        String email,
        @NotBlank
        String nickname,
        @NotBlank
        String password
) {
        public Customer toEntity(SignUpForm form, BCryptPasswordEncoder encoder) {
                return new Customer(form.email, form.nickname, encoder.encode(form.password));
        }
}
