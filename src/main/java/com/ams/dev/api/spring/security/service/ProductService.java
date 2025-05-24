package com.ams.dev.api.spring.security.service;

import com.ams.dev.api.spring.security.dto.ProductDto;
import com.ams.dev.api.spring.security.persistence.entity.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ProductService {
    Page<ProductEntity> findAll(Pageable pageable);

    Optional<ProductEntity> findById(Long productId);

    ProductEntity create(ProductDto productDto);

    ProductEntity update(Long productId, ProductDto productDto);

    ProductEntity disabledById(Long productId);
}
