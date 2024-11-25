package com.example.pupupprojectserver.domain.User;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    // username으로 사용자 정보 조회
    Optional<User> findByUsername(String username);

    // email로 사용자 정보 조회
    Optional<User> findByEmail(String email);
}
