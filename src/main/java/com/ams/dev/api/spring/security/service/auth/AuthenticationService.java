package com.ams.dev.api.spring.security.service.auth;

import com.ams.dev.api.spring.security.dto.UserDto;
import com.ams.dev.api.spring.security.persistence.entity.UserEntity;
import com.ams.dev.api.spring.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {


    @Autowired
    private UserService userService;

    @Autowired
    private JwtService jwtService;


    public UserDto register(UserDto userDto) {
        UserEntity user = userService.create(userDto);
        UserDto userRegister = new UserDto();
        userRegister.setId(user.getId());
        userRegister.setName(user.getName());
        userRegister.setUsername(user.getUsername());
        userRegister.setRole(user.getRole().name());

        //Generated Token
        String jwt = jwtService.generateToken(user);
        userRegister.setJwt(jwt);


        return userRegister;
    }
}
