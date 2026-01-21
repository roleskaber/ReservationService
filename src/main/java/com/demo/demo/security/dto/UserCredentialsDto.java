package com.demo.demo.security.dto;

import com.demo.demo.security.db.UserEntity;

public class UserCredentialsDto {
    private final String userName;
    private final String email;

    public UserCredentialsDto(String userName, String email) {
        this.userName = userName;
        this.email = email;
    }

    public UserEntity toEntity() {
        return new UserEntity(
                email,
                userName
        );
    }
}

