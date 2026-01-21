package com.demo.demo.security.db;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity getUserEntityByEmail(String email);
    UserEntity getUserEntityById(Long id);
}
