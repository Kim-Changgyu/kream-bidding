package com.example.kreambidding.model.product;

import lombok.AllArgsConstructor;

import java.util.Arrays;
import java.util.Optional;

@AllArgsConstructor
public enum Gender {
    MALE("남성"),
    FEMALE("여성"),
    KIDZ("키즈");

    private final String name;

    public static Optional<Gender> of(String name) {
        return Arrays.stream(Gender.values())
                .filter(gender -> gender.name.equals(name))
                .findFirst();
    }
}
