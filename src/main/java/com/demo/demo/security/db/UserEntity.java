package com.demo.demo.security.db;

import com.demo.demo.security.dto.UserCredentialsDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Table(name="users")
@Entity
public class UserEntity {
    @Id
    @Column(name = "id")
    Long id;
    @Column(name= "email")
    String email;
    @Column(name= "username")
    String username;

    public UserEntity(String email, String username) {
        this.email = email;
        this.username = username;
    }

    public UserCredentialsDto toDto () {
        return new UserCredentialsDto(username, email);
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
