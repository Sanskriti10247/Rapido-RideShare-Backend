package com.Sanskriti.Rapido.controller;

import com.Sanskriti.Rapido.dto.LoginRequest;
import com.Sanskriti.Rapido.dto.RegisterRequest;
import com.Sanskriti.Rapido.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public String register(@RequestBody @Valid RegisterRequest request) {
        authService.register(request);
        return "User registered successfully";
    }

    @PostMapping("/login")
    public String login(@RequestBody @Valid LoginRequest request) {
        return authService.login(request);
    }
}
