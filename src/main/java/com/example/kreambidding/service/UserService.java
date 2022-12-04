package com.example.kreambidding.service;

import com.example.kreambidding.controller.dto.UserDTO;
import com.example.kreambidding.model.user.User;
import com.example.kreambidding.repository.user.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;
import java.util.regex.Pattern;

@Service
@Transactional
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(UserDTO userDTO) {
        User user = new User();
        user.setName(userDTO.getName());
        user.setEmail(validateEmail(userDTO.getEmail()));
        user.setAddress(userDTO.getAddress());

        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(long id) {
        return userRepository.findById(id).orElseThrow();
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow();
    }

    public User updateUser(UserDTO userDTO) {
        User user = userRepository.findById(userDTO.getId()).orElseThrow();
        user.setName(userDTO.getName() != null? userDTO.getName(): user.getName());
        user.setAddress(userDTO.getAddress() != null? userDTO.getAddress(): user.getAddress());

        return userRepository.save(user);
    }

    public void deleteUser(long id) {
        userRepository.deleteById(id);
    }

    private String validateEmail(String email) {
        Assert.notNull(email, "이메일 주소는 비어있을 수 없습니다.");
        Assert.isTrue(email.length() >= 4 && email.length() <= 50, "이메일 주소는 4자 이상 50자 미이어야 합니다.");
        Assert.isTrue(Pattern.matches("\\b[\\w\\.-]+@[\\w\\.-]+\\.\\w{2,4}\\b", email), "유효한 이메일 형식이 아닙니다.");

        return email;
    }
}
