package me.progfrog.healthnewbie.user.domain.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "customer")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Customer extends TimeBaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "nickname", unique = true)
    private String nickname;

    @Column(name = "password")
    private String password;

    @Column(name = "verification_code")
    private String verificationCode;

    @Column(name = "verify_expired_at")
    private LocalDateTime verifyExpiredAt;

    @Column(name = "is_verify")
    private boolean verify;

    public Customer(String email, String nickname, String password) {
        this.email = email;
        this.nickname = nickname;
        this.password = password;
        this.verify = false;
    }

    public void updateVerificationInfo(String verificationCode, LocalDateTime verifyExpiredAt) {
        this.verificationCode = verificationCode;
        this.verifyExpiredAt = verifyExpiredAt;
    }

    public void updateVerify(boolean verify) {
        this.verify = verify;
    }
}
