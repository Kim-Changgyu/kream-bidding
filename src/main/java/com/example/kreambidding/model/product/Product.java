package com.example.kreambidding.model.product;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@Builder
@EqualsAndHashCode(of = { "productId" })
public class Product {
    @NonNull
    private final long productId;
    @NonNull
    private Category category;
    @NonNull
    private String brand;
    @NonNull
    private String productName;
    @NonNull
    private String serialNumber;
    private long price;
    private String color;
    private LocalDate releasedAt;
    private Gender gender;
    private String size;
}