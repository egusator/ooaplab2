package com.example.ooaplab2.service;

import com.example.ooaplab2.entity.Authority;
import com.example.ooaplab2.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class AuthorityService {

    private final UserRepository userRepository;

    public List<Authority> getAuthoritiesByEmail(String email) {
        Set<Authority> authoritySet = userRepository.getUserByEmail(email).getAuthoritySet();
        return authoritySet.stream().toList();
    }

}
