package com.ams.dev.api.spring.security.service.impl;

import com.ams.dev.api.spring.security.dto.UserDto;
import com.ams.dev.api.spring.security.exception.InvalidPasswordException;
import com.ams.dev.api.spring.security.persistence.entity.UserEntity;
import com.ams.dev.api.spring.security.persistence.repository.UserRepository;
import com.ams.dev.api.spring.security.persistence.util.Role;
import com.ams.dev.api.spring.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Optional;

@Service
public class UserServiceImpl  implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserEntity create(UserDto userDto) {

        validatePassword(userDto);

        UserEntity user = new UserEntity();
        user.setName(userDto.getName());
        user.setUsername(userDto.getUsername());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setRole(Role.ROLE_CUSTOMER);

        return userRepository.save(user);
    }

    @Override
    public Optional<UserEntity> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    private void validatePassword(UserDto userDto) {
        if (!StringUtils.hasText(userDto.getPassword()) || !StringUtils.hasText(userDto.getRepeatPassword())){
            throw new InvalidPasswordException("Password don't match");
        }

        if (!userDto.getPassword().equals(userDto.getRepeatPassword())){
            throw new InvalidPasswordException("Passwords don't match");
        }
    }
}
