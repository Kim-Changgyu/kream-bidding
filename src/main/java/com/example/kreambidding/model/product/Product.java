package com.example.kreambidding.model.product;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Category category;
    @Column(nullable = false)
    private String brand;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String serialNumber;
    private long price;
    private String color;
    private LocalDate releasedAt;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    private String size;
}