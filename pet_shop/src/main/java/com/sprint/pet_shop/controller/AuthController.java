package com.sprint.pet_shop.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class AuthController {

    @GetMapping("/me")
    public Map<String, String> getCurrentUser(Authentication authentication) {

        Map<String, String> user = new HashMap<>();
        user.put("username", authentication.getName());
        user.put("role", authentication.getAuthorities().toString());

        return user;
    }
}