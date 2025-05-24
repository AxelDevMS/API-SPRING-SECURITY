package com.ams.dev.api.spring.security.service;

import com.ams.dev.api.spring.security.dto.CategoryDto;
import com.ams.dev.api.spring.security.persistence.entity.CategoryEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface CategoryService {
    Page<CategoryEntity> findAll(Pageable pageable);

    Optional<CategoryEntity> findById(Long categoryId);

    CategoryEntity create(CategoryDto categoryDto);

    CategoryEntity update(Long categoryId, CategoryDto categoryDto);

    CategoryEntity disabledById(Long categoryId);
}
