package com.demo.demo.security.api;

import com.demo.demo.security.dto.JwtAuthDto;
import com.demo.demo.security.dto.RefreshTokenDto;
import com.demo.demo.security.dto.UserCredentialsDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@RestController
@RequestMapping("/user")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<UserCredentialsDto> getUserCredentialsById (
            @PathVariable Long id,
            @RequestHeader(HttpHeaders.AUTHORIZATION) String authorization
            ) {
        return ResponseEntity.ok(userService.getUserById(id, authorization.substring(7)));
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<UserCredentialsDto> getUserCredentialsByEmail (
            @PathVariable String email,
            @RequestHeader(HttpHeaders.AUTHORIZATION) String authorization
    ) {
        return ResponseEntity.ok(userService.getUserByEmail(email, authorization));
    }

    @PostMapping("/register")
    public ResponseEntity<JwtAuthDto> postUserCredentials (UserCredentialsDto userCredentialsDto) {
        return ResponseEntity.ok(userService.addUser(userCredentialsDto));
    }

    @PostMapping("/token/refresh")
    public ResponseEntity<JwtAuthDto> updateUserToken (RefreshTokenDto dto) {
        return ResponseEntity.ok(userService.refreshToken(dto));
    }

}
