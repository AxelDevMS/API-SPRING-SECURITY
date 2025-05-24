package com.ams.dev.api.spring.security.controller;

import com.ams.dev.api.spring.security.dto.ProductDto;
import com.ams.dev.api.spring.security.persistence.entity.ProductEntity;
import com.ams.dev.api.spring.security.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/alls")
    public ResponseEntity<Page<ProductEntity>> findAll(Pageable pageable){
        Page<ProductEntity> productsPage = productService.findAll(pageable);
        if (productsPage.hasContent())
            return ResponseEntity.ok(productsPage);

        return ResponseEntity.notFound().build();
    }

    @GetMapping("/get/{productId}")
    public ResponseEntity<ProductEntity> findById(@PathVariable Long productId){
        Optional<ProductEntity> product = productService.findById(productId);
        if (product.isPresent())
            return ResponseEntity.ok(product.get());

        return ResponseEntity.notFound().build();
    }

    @PostMapping("/save")
    public ResponseEntity<ProductEntity> create(@RequestBody @Valid ProductDto productDto){
        ProductEntity product =  productService.create(productDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(product);
    }

    @PutMapping("/update/{productId}")
    public ResponseEntity<ProductEntity> update(@PathVariable Long productId, @RequestBody @Valid ProductDto productDto){
        ProductEntity product =  productService.update(productId, productDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(product);
    }

    @PutMapping("/disabled/{productId}")
    public ResponseEntity<ProductEntity> disabledById(@PathVariable Long productId){
        ProductEntity product =  productService.disabledById(productId);
        return ResponseEntity.status(HttpStatus.CREATED).body(product);
    }
}
