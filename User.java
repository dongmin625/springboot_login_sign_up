package com.example.pupupprojectserver.domain.User;

import com.example.pupupprojectserver.domain.User.dto.UserDTO;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import java.sql.Timestamp;

//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@NoArgsConstructor
@Data
@DynamicInsert
@Entity
@Table(name = "user_tb")
public class User { //UserDetails 상속하여 인증 객체로 사용

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, unique = true)
    private String username; // 로그인 아이디

    @Column(nullable = false, unique = true)
    private String password; // 패스워드
    private String nickname; // 회원 별명

    @ColumnDefault("true")
    private boolean status; // 계정 상태 (false : 비활성, true : 활성)
    private String name;
    private String tel; // 전화번호

    @Column(nullable = false , unique = true)
    private String email;

    @ColumnDefault("'default/avatar.png'") //TODO : 나중에 upload 파일 생성하기
    private String imgFilename;

    @CreationTimestamp
    private Timestamp registeredAt; // 가입일

    @Builder
    public User(long id, String username, String password, String nickname, boolean status, String name, String tel, String email, String imgFilename, Timestamp registeredAt) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.status = status;
        this.name = name;
        this.tel = tel;
        this.email = email;
        this.imgFilename = imgFilename;
        this.registeredAt = registeredAt;
    }

    public UserDTO toDTO() {
        return UserDTO.toUserDTO(this);  // 이미 UserDTO 클래스에 정의된 static 메서드를 사용
    }

//    @Override //권한 반환
//    public Collection<? extends GrantedAuthority> getAuthorities(){
//        return List.of(new SimpleGrantedAuthority("user"));
//    }

    // id: username 반환
    public String getUsername(){
        return username;
    }

    // 패스워드 반환
    public String getPassword(){
        return password;
    }

    // 계정 만료 여부 반환
    public boolean isAccountNonExpired(){
        //만료되었는지 확인 로직 추가
        return true; // ture -> 만료되지 않음
    }

    // 계정 잠금 여부 반환
    public boolean isAccountNonLocked(){
        //계정이 잠금 되었는지 확인하는 로직
        return true; // ture -> 잠금되지 않음
    }

    // 패스워드 만료 여부 반환
    public boolean isCredentialsNonExpired(){
        //패스워드가 만료되었는지 확인하는 로직
        return true; // true -> 만료되지 않음
    }

    // 계정 사용 가능 여부 반환
    public boolean isEnabled(){
        //계정이 사용 가능한지 확인하는 로직
        return true; // true -> 사용가능
    }
}
