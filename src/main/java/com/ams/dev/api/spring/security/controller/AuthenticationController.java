package com.ams.dev.api.spring.security.controller;

import com.ams.dev.api.spring.security.dto.ResponseAuthenticationDto;
import com.ams.dev.api.spring.security.service.auth.AuthenticationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;


   @GetMapping("/validate")
   public ResponseEntity<Boolean> validate(@RequestParam String jwt){
       boolean isTokenValid = authenticationService.validateToken(jwt);
       return ResponseEntity.ok(isTokenValid);
   }


    @PostMapping("/login")
    public ResponseEntity<ResponseAuthenticationDto> login(@RequestBody @Valid ResponseAuthenticationDto responseAuthenticationDto){
        ResponseAuthenticationDto response = authenticationService.login(responseAuthenticationDto);
        return ResponseEntity.ok(response);
    }
}
