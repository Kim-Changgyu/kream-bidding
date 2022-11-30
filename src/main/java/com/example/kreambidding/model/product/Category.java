package com.example.kreambidding.model.product;

import lombok.AllArgsConstructor;

import java.util.Arrays;
import java.util.Optional;

@AllArgsConstructor
public enum Category {
    SHOES("신발"),
    CLOTHING("의류"),
    FASHION_GOODS("패션잡화"),
    LIFE("라이프"),
    TECH("테크");

    private final String name;

    public static Optional<Category> of(String name) {
        return Arrays.stream(Category.values())
                .filter(category -> category.name.equals(name))
                .findFirst();
    }
}
