package com.ams.dev.api.spring.security.service;

import com.ams.dev.api.spring.security.dto.UserDto;
import com.ams.dev.api.spring.security.persistence.entity.UserEntity;

import java.util.Optional;

public interface UserService {
    UserEntity create(UserDto userDto);
    Optional<UserEntity> findByUsername(String username);
}
