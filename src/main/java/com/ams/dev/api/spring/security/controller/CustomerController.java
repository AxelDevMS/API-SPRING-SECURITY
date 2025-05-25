package com.ams.dev.api.spring.security.controller;

import com.ams.dev.api.spring.security.dto.UserDto;
import com.ams.dev.api.spring.security.service.auth.AuthenticationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customers")
public class CustomerController {


    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/save")
    public ResponseEntity<UserDto> register(@RequestBody @Valid UserDto userDto){
        UserDto user = this.authenticationService.register(userDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }


}
