package com.example.kreambidding.controller.dto;

import com.example.kreambidding.model.product.Category;
import com.example.kreambidding.model.product.Gender;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class ProductDTO {
    private Long id;
    @NonNull
    private Category category;
    @NonNull
    private String brand;
    @NonNull
    private String name;
    @NonNull
    private String serialNumber;
    private long price;
    private String color;
    private LocalDate releasedAt;
    private Gender gender;
    private String size;
}
