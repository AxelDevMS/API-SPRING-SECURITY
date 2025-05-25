package com.ams.dev.api.spring.security.service.auth;

import com.ams.dev.api.spring.security.persistence.entity.UserEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class JwtService {

    public String generateToken(UserDetails user) {
        return null;
    }
}
