package com.ams.dev.api.spring.security.persistence.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "category")
public class CategoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private CategoryStatus status;


    public static enum CategoryStatus{
        ENABLED, DISABLED
    }
}
