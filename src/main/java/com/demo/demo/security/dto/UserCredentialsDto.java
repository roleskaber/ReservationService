package com.demo.demo.security.dto;

import com.demo.demo.security.db.UserEntity;

public record UserCredentialsDto (
        String userName,
        String email
) {
    public UserEntity toEntity() {
        return new UserEntity(
                email,
                userName
        );
    }
}

