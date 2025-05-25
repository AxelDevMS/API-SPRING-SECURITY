package com.ams.dev.api.spring.security.service.auth;

import com.ams.dev.api.spring.security.dto.UserDto;
import com.ams.dev.api.spring.security.persistence.entity.UserEntity;
import com.ams.dev.api.spring.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

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
        String jwt = jwtService.generateToken(user, generateExtraClaims(user));
        userRegister.setJwt(jwt);


        return userRegister;
    }

    private Map<String, Object> generateExtraClaims(UserEntity user) {
        Map<String, Object> extraClaims = new HashMap<>();
        extraClaims.put("name",user.getName());
        extraClaims.put("role",user.getRole().name());
        extraClaims.put("authorities",user.getAuthorities());
        return extraClaims;
    }
}
