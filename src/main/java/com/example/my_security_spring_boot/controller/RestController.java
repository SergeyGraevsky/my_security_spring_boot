package com.example.my_security_spring_boot.controller;

import com.example.my_security_spring_boot.models.User;
import com.example.my_security_spring_boot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@org.springframework.web.bind.annotation.RestController
@RequestMapping(value = "/adminPage/")
public class RestController {

    private final UserService userService;

    @Autowired
    public RestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/userById/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(userService.findById(id));
    }

    @PostMapping("/new")
    public ResponseEntity<User> create(@ModelAttribute User user) {
        return ResponseEntity.ok().body(userService.saveUser(user));
    }

    @PostMapping("/user/update")
    public ResponseEntity<User> update(@ModelAttribute User user) {
        return ResponseEntity.ok().body(userService.saveUser(user));
    }

    @GetMapping("/user/delete/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable String id) {
        return ResponseEntity.ok().body(userService.deleteById(Long.parseLong(id)));
    }
}
