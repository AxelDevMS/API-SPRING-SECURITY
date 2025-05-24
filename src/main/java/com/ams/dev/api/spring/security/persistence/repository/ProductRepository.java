package com.ams.dev.api.spring.security.persistence.repository;

import com.ams.dev.api.spring.security.persistence.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<ProductEntity,Long> {
}
