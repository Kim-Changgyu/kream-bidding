package com.example.kreambidding.repository.user;

import com.example.kreambidding.model.user.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    User insert(User user);

    List<User> findAll();

    Optional<User> findById(long userId);

    User update(User user);

    void delete(long userId);

    void deleteAll();
}
