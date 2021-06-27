package com.example.my_security_spring_boot.controller;

import com.example.my_security_spring_boot.models.User;
import com.example.my_security_spring_boot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
public class AdminController {

    private final UserService userService;

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/admin")
    public String index(@AuthenticationPrincipal User activeUser,
                        Model model) {
        model.addAttribute("users", userService.findAll());
        model.addAttribute("roles", activeUser.getRoles());
        model.addAttribute("activeUser", activeUser);
        return "admin";
    }
}
