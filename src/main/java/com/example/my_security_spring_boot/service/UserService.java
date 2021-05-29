package com.example.my_security_spring_boot.service;

import com.example.my_security_spring_boot.models.User;

import java.util.List;

public interface UserService {

    User findById(Long id);

    List<User> findAll();

    User saveUser(User user);

    void deleteById(Long id);

    User getUserByPassword(String pass);

    User findByUsername(String username);

}
