package com.Sanskriti.Rapido.service;

import com.Sanskriti.Rapido.dto.LoginRequest;
import com.Sanskriti.Rapido.dto.RegisterRequest;
import com.Sanskriti.Rapido.exception.BadRequestException;
import com.Sanskriti.Rapido.model.User;
import com.Sanskriti.Rapido.repository.UserRepository;
import com.Sanskriti.Rapido.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    // ✅ REGISTER WITH DTO
    public void register(RegisterRequest request) {

        User existing = userRepository.findByUsername(request.getUsername());
        if (existing != null) {
            throw new BadRequestException("User already exists");
        }

        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(request.getRole());

        userRepository.save(user);
    }

    // ✅ LOGIN WITH DTO
    public String login(LoginRequest request) {

        User user = userRepository.findByUsername(request.getUsername());
        if (user == null) {
            throw new BadRequestException("Invalid username or password");
        }

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new BadRequestException("Invalid username or password");
        }

        return jwtUtil.generateToken(user.getUsername(), user.getRole());
    }
}
