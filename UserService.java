package com.example.pupupprojectserver.domain.User;

import com.example.pupupprojectserver.domain.User.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
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
}


