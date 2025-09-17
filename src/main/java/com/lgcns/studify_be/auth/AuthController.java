package com.lgcns.studify_be.auth;

import com.lgcns.studify_be.security.JwtTokenProvider;
import com.lgcns.studify_be.user.User;
import com.lgcns.studify_be.user.UserRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    // 회원가입 (테스트용 간단 버전)
    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody SignUpReq req) {
        if (userRepository.existsByEmail(req.getEmail())) {
            return ResponseEntity.badRequest().body("Email already exists");
        }

        User u = User.builder()
                .email(req.getEmail())
                .passwordHash(passwordEncoder.encode(req.getPassword())) // passwordHash에 저장
                .build();
        userRepository.save(u);

        return ResponseEntity.ok("Signup success");
    }

    // 로그인 → JWT 발급
    @PostMapping("/login")
    public ResponseEntity<TokenRes> login(@RequestBody LoginReq req) {
        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(req.getEmail(), req.getPassword())
        );

        // 인증 성공 → 토큰 발급
        String accessToken = jwtTokenProvider.createAccessToken(req.getEmail());
        String refreshToken = jwtTokenProvider.createRefreshToken(req.getEmail());

        return ResponseEntity.ok(new TokenRes(accessToken, refreshToken));
    }

    // 요청 DTO
    @Data
    static class SignUpReq {
        private String email;
        private String password;
    }

    @Data
    static class LoginReq {
        private String email;
        private String password;
    }

    // 응답 DTO
    @Data
    static class TokenRes {
        private final String accessToken;
        private final String refreshToken;
    }
}
