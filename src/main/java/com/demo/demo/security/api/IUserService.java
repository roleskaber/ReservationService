package com.demo.demo.security.api;

import com.demo.demo.security.dto.JwtAuthDto;
import com.demo.demo.security.dto.RefreshTokenDto;
import com.demo.demo.security.dto.UserCredentialsDto;
import com.demo.demo.security.jwt.JwtService;

import java.util.NoSuchElementException;

public interface IUserService {
    JwtAuthDto refreshToken(RefreshTokenDto refreshToken);
    UserCredentialsDto getUserById(Long id, String auth) throws NoSuchElementException;
    UserCredentialsDto getUserByEmail(String email, String auth) throws NoSuchElementException;
    JwtAuthDto addUser(UserCredentialsDto userCredentials);
}
