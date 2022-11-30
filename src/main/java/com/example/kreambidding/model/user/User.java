package com.example.kreambidding.model.user;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@Builder
@EqualsAndHashCode
public class User {
    private final long userId;
    @NonNull
    private String userName;
    @NonNull
    private String address;
}