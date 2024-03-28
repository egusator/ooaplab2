package com.example.ooaplab2.repository;

import com.example.ooaplab2.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Long> {
    User getUserByEmail(String email);
}
