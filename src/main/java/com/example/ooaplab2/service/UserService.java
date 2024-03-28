package com.example.ooaplab2.service;

import com.example.ooaplab2.entity.User;
import com.example.ooaplab2.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    public Long getUserIdByEmail(String email) {
        User user = userRepository.getUserByEmail(email);
        return user.getId();
    }
}
