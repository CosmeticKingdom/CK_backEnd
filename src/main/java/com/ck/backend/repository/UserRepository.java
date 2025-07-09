package com.ck.backend.repository;

import com.ck.backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // 이메일로 사용자 찾기
    Optional<User> findByEmail(String email);
    // 사용자 ID로 사용자 찾기
    Optional<User> findByUsername(String username);
}
