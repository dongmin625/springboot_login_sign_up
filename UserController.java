package com.example.pupupprojectserver.domain.User;

import com.example.pupupprojectserver.domain.User.dto.LoginDTO;
import com.example.pupupprojectserver.domain.User.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    // 유저 생성
    @PostMapping
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) {
        User user = User.builder()
                .username(userDTO.getUsername())
                .password(userDTO.getPassword())
                .email(userDTO.getEmail())
                .build();
        UserDTO savedUserDTO = userService.saveUser(user); // UserDTO 반환
        return new ResponseEntity<>(savedUserDTO, HttpStatus.CREATED);
    }

    // 이메일로 유저 조회
    @GetMapping("/{email}")
    public ResponseEntity<UserDTO> getUserByEmail(@PathVariable String email) {
        Optional<UserDTO> userDTO = userService.findUserByEmail(email); // Optional<UserDTO> 반환
        return userDTO.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // 모든 유저 조회
    @GetMapping("/all")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserDTO> userDTOs = userService.findAllUsers(); // List<UserDTO> 반환
        return ResponseEntity.ok(userDTOs);
    }


    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody LoginDTO loginDTO) {
        boolean isSuccess = userService.login(loginDTO.getUsername(), loginDTO.getPassword());

        Map<String, String> response = new HashMap<>();
        if (isSuccess) {
            response.put("message", "로그인 성공");
            return ResponseEntity.ok(response);
        } else {
            response.put("message", "로그인 실패");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
    }


}





