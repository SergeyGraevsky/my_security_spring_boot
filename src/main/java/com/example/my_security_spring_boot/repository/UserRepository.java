package com.example.my_security_spring_boot.repository;

import com.example.my_security_spring_boot.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User getUserByPassword(String pass);

    User findByUsername(String username);

}
