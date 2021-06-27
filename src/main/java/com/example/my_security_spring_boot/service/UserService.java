package com.example.my_security_spring_boot.service;

import com.example.my_security_spring_boot.models.User;

import java.util.List;

public interface UserService {

    User findById(Long id);

    List<User> findAll();

    User saveUser(User user);

    boolean deleteById(Long id);

    User findByUsername(String username);
}
