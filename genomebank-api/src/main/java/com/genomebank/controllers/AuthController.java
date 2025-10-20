package com.genomebank.controllers;

import com.genomebank.dto.auth.AuthResponse;
import com.genomebank.dto.auth.LoginRequest;
import com.genomebank.dto.in.UserInDTO;
import com.genomebank.entities.Role;
import com.genomebank.entities.User;
import com.genomebank.services.impl.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    // POST /auth/register → Crear cuenta de usuario (rol USER por defecto)
    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody UserInDTO dto) {git
        if (dto.getRole() == null) {
            dto.setRole(Role.USER);
        }
        return ResponseEntity.ok(authService.register(dto));
    }

    // POST /auth/login → Iniciar sesión y obtener token JWT
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok(authService.login(loginRequest));
    }
}
