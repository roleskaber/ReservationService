package com.demo.demo.security.api;

import com.demo.demo.security.db.UserEntity;
import com.demo.demo.security.db.UserRepository;
import com.demo.demo.security.dto.JwtAuthDto;
import com.demo.demo.security.dto.RefreshTokenDto;
import com.demo.demo.security.dto.UserCredentialsDto;
import com.demo.demo.security.jwt.JwtService;
import jakarta.transaction.Transactional;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Objects;

@Service
public class UserService implements IUserService {
    private final JwtService jwtService;
    private final UserRepository userRepository;

    public UserService(JwtService jwtService, UserRepository userRepository) {
        this.jwtService = jwtService;
        this.userRepository = userRepository;
    }

    @Override
    public JwtAuthDto refreshToken(RefreshTokenDto dto) {
        String email = jwtService.getEmailFromToken(dto.token());
        return jwtService.refreshBaseToken(email, dto.refreshToken());
    }

    @Override
    @Transactional
    public UserCredentialsDto getUserById(Long id, String auth) throws NoSuchElementException {
        UserEntity expected_user = userRepository.getUserEntityById(id);
        String email_token = jwtService.getEmailFromToken(auth);
        UserEntity real_user = userRepository.getUserEntityByEmail(email_token);
        if (expected_user == real_user) {
            return expected_user.toDto();
        }
        throw new NoSuchElementException("Us not found");
    }

    @Override
    @Transactional
    public UserCredentialsDto getUserByEmail(String email, String auth) throws NoSuchElementException {
        String email_token = jwtService.getEmailFromToken(auth);
        if (Objects.equals(email_token, email)) {
            return userRepository.getUserEntityByEmail(email).toDto();
        }
        throw new NoSuchElementException("Us not found");
    }

    @Override
    @Transactional
    public JwtAuthDto addUser(UserCredentialsDto userCredentials) {
        var result = userRepository.save(userCredentials.toEntity());
        return new JwtAuthDto(
                jwtService.generateJwtToken(result.getEmail()),
                jwtService.generateRefreshToken(result.getEmail())
        );
    }
}
