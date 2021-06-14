package com.example.my_security_spring_boot.controller;

import com.example.my_security_spring_boot.models.Role;
import com.example.my_security_spring_boot.models.User;
import com.example.my_security_spring_boot.service.RoleService;
import com.example.my_security_spring_boot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@Controller
@RequestMapping("/")
public class AdminController {

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/admin")
    public String index(@AuthenticationPrincipal User activeUser,
                        Model model,
                        @ModelAttribute("user") User user) {
        model.addAttribute("users", userService.findAll());
        model.addAttribute("roles", activeUser.getRoles());
        model.addAttribute("activeUser", activeUser);
        return "admin";
    }

    @PostMapping("/new")
    public String create(@ModelAttribute("user") User user, @RequestParam("role") ArrayList<String> role) {
        if (userService.getUserByPassword(user.getPassword()) != null) return "userExists";
        Set<Role> roleSet = new HashSet<>();
        for (String roleId : role) {
            roleSet.add(roleService.getById(Long.valueOf(roleId)));
        }
        user.setRoles(roleSet);
        userService.saveUser(user);
        return "redirect:/admin";
    }

    @PostMapping("/user/{id}")
    public String update(@ModelAttribute("user") User user, @RequestParam ArrayList<String> role, @PathVariable("id") long id) {
        Set<Role> roleSet = new HashSet<>();
        for (String roleId : role) {
            roleSet.add(roleService.getById(Long.valueOf(roleId)));
        }
        user.setRoles(roleSet);
        userService.saveUser(user);
        return "redirect:/admin";
    }

    @PostMapping("/user/delete/{id}")
    public String delete(@PathVariable("id") long id) {
        userService.deleteById(id);
        return "redirect:/admin";
    }
}
