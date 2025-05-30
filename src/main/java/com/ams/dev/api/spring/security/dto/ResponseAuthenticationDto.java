package com.ams.dev.api.spring.security.dto;

import java.io.Serializable;

public class ResponseAuthenticationDto implements Serializable {

    private String username;

    private String password;

    //login
    private String jwt;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }
}
