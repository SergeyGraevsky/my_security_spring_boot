package com.example.my_security_spring_boot.controller;

import com.example.my_security_spring_boot.models.Role;
import com.example.my_security_spring_boot.models.User;
import com.example.my_security_spring_boot.service.RoleService;
import com.example.my_security_spring_boot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public String index(Model model) {
        model.addAttribute("users", userService.findAll());
        return "admin";
    }

    @GetMapping("/user/{id}")
    public String show(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user", userService.findById(id));
        return "showuser";
    }

    @GetMapping("/new")
    public String newUser(@ModelAttribute("user") User user) {
        return "new";
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

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") long id, Model model) {
        model.addAttribute("user", userService.findById(id));
        return "edit";
    }

    @PostMapping("/user/{id}")
    public String update(@ModelAttribute("user") User user, @RequestParam ArrayList<String> role) {
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
