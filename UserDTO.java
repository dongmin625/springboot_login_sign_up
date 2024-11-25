package com.example.pupupprojectserver.domain.User.dto;

import com.example.pupupprojectserver.domain.User.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class UserDTO { //회원 정보 필드로 정의
    private long id;
    private String username;
    private String password;
    private String email;

    //builder 패턴으로 바꾸는것도 고려
    public static UserDTO toUserDTO(User user){

        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setUsername(user.getUsername());
        userDTO.setPassword(user.getPassword());
        userDTO.setEmail(user.getEmail());

        return userDTO;

    }
}
