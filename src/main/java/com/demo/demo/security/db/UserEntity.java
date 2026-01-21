package com.demo.demo.security.db;

import com.demo.demo.security.CustomUserDetails;
import com.demo.demo.security.dto.UserCredentialsDto;
import jakarta.persistence.*;

@Table(name="users")
@Entity
public class UserEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(name= "email")
    String email;
    @Column(name= "username")
    String username;

    public UserEntity(String email, String username) {
        this.email = email;
        this.username = username;
    }

    public UserEntity() {
    }

    public UserCredentialsDto toDto () {
        return new UserCredentialsDto(username, email);
    }

    public CustomUserDetails toCustomDetails () {
        return new CustomUserDetails(this);
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }
}
