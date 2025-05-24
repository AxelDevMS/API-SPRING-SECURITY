package com.ams.dev.api.spring.security.service.impl;

import com.ams.dev.api.spring.security.dto.ProductDto;
import com.ams.dev.api.spring.security.exception.ObjectNotFoundException;
import com.ams.dev.api.spring.security.persistence.entity.CategoryEntity;
import com.ams.dev.api.spring.security.persistence.entity.ProductEntity;
import com.ams.dev.api.spring.security.persistence.repository.ProductRepository;
import com.ams.dev.api.spring.security.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Page<ProductEntity> findAll(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    @Override
    public Optional<ProductEntity> findById(Long productId) {
        return productRepository.findById(productId);
    }

    @Override
    public ProductEntity create(ProductDto productDto) {
        ProductEntity product = new ProductEntity();
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        product.setStatus(ProductEntity.ProductStatus.ENABLED);

        CategoryEntity category = new CategoryEntity();
        category.setId(productDto.getCategoryId());
        product.setCategory(category);

        return productRepository.save(product);
    }

    @Override
    public ProductEntity update(Long productId, ProductDto productDto) {

        ProductEntity productFromDB = productRepository.findById(productId)
                .orElseThrow( () -> new ObjectNotFoundException("Product not ofund with id " + productId));

        productFromDB.setName(productDto.getName());
        productFromDB.setPrice(productDto.getPrice());

        CategoryEntity category = new CategoryEntity();
        category.setId(productDto.getCategoryId());
        productFromDB.setCategory(category);

        return productRepository.save(productFromDB);
    }

    @Override
    public ProductEntity disabledById(Long productId) {
        ProductEntity productFromDB = productRepository.findById(productId)
                .orElseThrow( () -> new ObjectNotFoundException("Product not ofund with id " + productId));

        productFromDB.setStatus(ProductEntity.ProductStatus.DISABLED);

        return productRepository.save(productFromDB);
    }
}
