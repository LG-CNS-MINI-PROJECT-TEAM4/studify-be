package com.lgcns.studify_be.user;

import com.lgcns.studify_be.user.dto.UserDtos;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service @RequiredArgsConstructor
public class UserService {
    private final UserRepository repo;
    private final PasswordEncoder encoder;

    @Transactional
    public User create(UserDtos.Create dto) {
        if (repo.existsByEmail(dto.email())) throw new IllegalArgumentException("이미 사용 중인 이메일입니다.");
        User u = User.builder()
                .email(dto.email())
                .passwordHash(encoder.encode(dto.password()))
                .nickname(dto.nickname())
                .status(UserStatus.ACTIVE)
                .build();
        return repo.save(u);
    }

    @Transactional(readOnly = true)
    public User get(Long id) {
        return repo.findById(id).orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다: " + id));
    }

    @Transactional
    public User update(Long id, UserDtos.Update dto) {
        User u = get(id);
        if (dto.nickname() != null) u.setNickname(dto.nickname());
        if (dto.status() != null)   u.setStatus(dto.status());
        if (dto.password() != null) u.setPasswordHash(encoder.encode(dto.password()));
        return u; // 더티체킹
    }

    @Transactional public void delete(Long id) { repo.delete(get(id)); }

    @Transactional(readOnly = true)
    public Page<User> list(Pageable pageable) { return repo.findAll(pageable); }
}
