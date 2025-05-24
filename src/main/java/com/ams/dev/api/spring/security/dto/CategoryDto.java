package com.ams.dev.api.spring.security.dto;

import com.ams.dev.api.spring.security.persistence.entity.CategoryEntity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;

import java.io.Serializable;

public class CategoryDto implements Serializable {

    private Long id;

    @NotBlank
    private String name;

    private String status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
