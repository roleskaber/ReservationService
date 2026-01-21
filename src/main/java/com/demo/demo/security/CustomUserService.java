package com.demo.demo.security;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class CustomUserService implements UserDetailsService {
    @Override
    public CustomUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }
}
