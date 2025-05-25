package com.ams.dev.api.spring.security.service;

import com.ams.dev.api.spring.security.dto.UserDto;
import com.ams.dev.api.spring.security.persistence.entity.UserEntity;

public interface UserService {
    UserEntity create(UserDto userDto);
}
