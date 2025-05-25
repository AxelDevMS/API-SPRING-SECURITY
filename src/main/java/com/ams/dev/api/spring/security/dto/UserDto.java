package com.ams.dev.api.spring.security.dto;

import jakarta.validation.constraints.Size;

import java.io.Serializable;

public class UserDto implements Serializable {
    private Long id;

    @Size(min = 4)

    private String username;

    @Size(min = 4)
    private String name;

    @Size(min = 8)
    private String password;

    @Size(min = 8)
    private String repeatPassword;

    private String role;

    private String jwt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRepeatPassword() {
        return repeatPassword;
    }

    public void setRepeatPassword(String repeatPassword) {
        this.repeatPassword = repeatPassword;
    }
}
