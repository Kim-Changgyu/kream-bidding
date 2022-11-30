package com.example.kreambidding.model.user;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@Builder
@EqualsAndHashCode(of = { "userId" })
public class User {
    @NonNull
    private final long userId;
    @NonNull
    private String userName;
    @NonNull
    private String address;
}