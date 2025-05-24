package com.ams.dev.api.spring.security.persistence.repository;

import com.ams.dev.api.spring.security.persistence.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<CategoryEntity,Long> {
}
