package com.example.my_security_spring_boot.service;

import com.example.my_security_spring_boot.models.Role;

import java.util.List;

public interface RoleService {

    List<Role> findAll();

    Role getById(Long id);

}
