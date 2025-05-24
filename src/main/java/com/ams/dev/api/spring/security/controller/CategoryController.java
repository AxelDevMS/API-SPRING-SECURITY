package com.ams.dev.api.spring.security.controller;

import com.ams.dev.api.spring.security.dto.CategoryDto;
import com.ams.dev.api.spring.security.dto.ProductDto;
import com.ams.dev.api.spring.security.persistence.entity.CategoryEntity;
import com.ams.dev.api.spring.security.persistence.entity.ProductEntity;
import com.ams.dev.api.spring.security.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/alls")
    public ResponseEntity<Page<CategoryEntity>> findAll(Pageable pageable){

        Page<CategoryEntity> categoriesPage = categoryService.findAll(pageable);

        if (categoriesPage.hasContent())
            return ResponseEntity.ok(categoriesPage);

        return ResponseEntity.notFound().build();
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<CategoryEntity> findById(@PathVariable Long categoryId){
        Optional<CategoryEntity> category = categoryService.findById(categoryId);
        if (category.isPresent())
            return ResponseEntity.ok(category.get());

        return ResponseEntity.notFound().build();
    }

    @PostMapping("/save")
    public ResponseEntity<CategoryEntity> create(@RequestBody @Valid CategoryDto categoryDto){
        CategoryEntity category =  categoryService.create(categoryDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(category);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<CategoryEntity> update(@PathVariable Long categoryId, @RequestBody @Valid CategoryDto categoryDto){
        CategoryEntity category =  categoryService.update(categoryId,categoryDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(category);
    }

    @PutMapping("/disabled/{id}")
    public ResponseEntity<CategoryEntity> disabledById(@PathVariable Long categoryId){
        CategoryEntity category =  categoryService.disabledById(categoryId);
        return ResponseEntity.status(HttpStatus.CREATED).body(category);
    }
}
