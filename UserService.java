package com.example.pupupprojectserver.domain.User;

import com.example.pupupprojectserver.domain.User.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    // 유저 저장
    public UserDTO saveUser(User user) {
        User savedUser = userRepository.save(user);
        return UserDTO.toUserDTO(savedUser); // UserDTO로 변환하여 반환
    }

    // email로 유저 조회
    public Optional<UserDTO> findUserByEmail(String email) {
        Optional<User> user = userRepository.findByEmail(email); // Optional<User> 반환
        return user.map(UserDTO::toUserDTO); // UserDTO로 변환하여 반환
    }

    // 모든 유저 조회
    public List<UserDTO> findAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(UserDTO::toUserDTO) // 각 User를 UserDTO로 변환
                .collect(Collectors.toList());
    }
    // 로그인 전달
    public boolean login(String loginId, String loginPassword) {
        User user = userRepository.findByUsername(loginId)
                .orElseThrow(() -> new IllegalArgumentException("아이디를 다시 확인해주세요"));

        // 비밀번호가 일치하는지 확인
        return isMatchedPassword(user, loginPassword);
    }

    private boolean isMatchedPassword(User user, String loginPassword) {
        // 입력된 비밀번호와 유저의 저장된 비밀번호를 비교
        return user.getPassword().equals(loginPassword);
    }


}





