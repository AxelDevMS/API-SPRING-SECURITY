package com.ams.dev.api.spring.security.service.impl;

import com.ams.dev.api.spring.security.dto.CategoryDto;
import com.ams.dev.api.spring.security.dto.ProductDto;
import com.ams.dev.api.spring.security.exception.ObjectNotFoundException;
import com.ams.dev.api.spring.security.persistence.entity.CategoryEntity;
import com.ams.dev.api.spring.security.persistence.entity.ProductEntity;
import com.ams.dev.api.spring.security.persistence.repository.CategoryRepository;
import com.ams.dev.api.spring.security.persistence.repository.ProductRepository;
import com.ams.dev.api.spring.security.service.CategoryService;
import com.ams.dev.api.spring.security.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Page<CategoryEntity> findAll(Pageable pageable) {
        return categoryRepository.findAll(pageable);
    }

    @Override
    public Optional<CategoryEntity> findById(Long categoryId) {
        return categoryRepository.findById(categoryId);
    }

    @Override
    public CategoryEntity create(CategoryDto categoryDto) {
        CategoryEntity category = new CategoryEntity();
        category.setName(categoryDto.getName());
        category.setStatus(CategoryEntity.CategoryStatus.ENABLED);
        return categoryRepository.save(category);
    }

    @Override
    public CategoryEntity update(Long categoryId, CategoryDto categoryDto) {
        CategoryEntity categoryFromDB = categoryRepository.findById(categoryId)
                        .orElseThrow(() -> new ObjectNotFoundException("Category not found with id "+categoryId));

        categoryFromDB.setName(categoryDto.getName());
        return categoryRepository.save(categoryFromDB);
    }

    @Override
    public CategoryEntity disabledById(Long categoryId) {
        CategoryEntity categoryFromDB = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ObjectNotFoundException("Category not found with id "+categoryId));

        categoryFromDB.setStatus(CategoryEntity.CategoryStatus.DISABLED);
        return categoryRepository.save(categoryFromDB);
    }
}
