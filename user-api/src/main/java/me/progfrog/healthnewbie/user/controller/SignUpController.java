package me.progfrog.healthnewbie.user.controller;

import lombok.RequiredArgsConstructor;
import me.progfrog.healthnewbie.user.application.SignUpApplication;
import me.progfrog.healthnewbie.user.domain.dto.auth.SignUpForm;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/signup")
@RequiredArgsConstructor
public class SignUpController {

    private final SignUpApplication signUpApplication;

    @PostMapping
    public ResponseEntity<String> customerSignUp(@RequestBody SignUpForm signUpForm) {
        signUpApplication.signUpCustomer(signUpForm);
        return ResponseEntity.ok("회원 가입용 인증 메일이 발송되었습니다.");
    }

    @GetMapping("/verify/customer")
    public ResponseEntity<String> verifyCustomer(@RequestParam("email") String email, @RequestParam("code") String code) {
        signUpApplication.verifyCustomer(email, code);
        return ResponseEntity.ok("인증이 완료되었습니다.");
    }
}
