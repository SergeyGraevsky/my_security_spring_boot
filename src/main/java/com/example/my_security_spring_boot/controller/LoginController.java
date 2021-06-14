package com.example.my_security_spring_boot.controller;

import com.example.my_security_spring_boot.models.User;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class LoginController {

    @GetMapping("/hello")
    public String printWelcome(@AuthenticationPrincipal User activeUser, Model model) {
        model.addAttribute("roles", activeUser.getRoles());
        model.addAttribute("activeUser", activeUser);
        return "hello";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }
}
