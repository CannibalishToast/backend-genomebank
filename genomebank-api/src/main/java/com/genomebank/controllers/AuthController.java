package com.genomebank.controllers;

import com.genomebank.dto.auth.AuthResponse;
import com.genomebank.dto.auth.LoginRequest;
import com.genomebank.dto.in.UserInDTO;
import com.genomebank.entities.Role;
import com.genomebank.services.IAuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final IAuthService authService;


    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody UserInDTO dto) {
        try {
            // Si el rol no se especifica, asignar USER por defecto
            if (dto.getRole() == null) {
                dto.setRole(Role.USER);
            }

            AuthResponse response = authService.register(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);

        } catch (RuntimeException e) {
            // Ejemplo: "El email ya está registrado"
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }


    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest loginRequest) {
        try {
            AuthResponse response = authService.login(loginRequest);
            return ResponseEntity.ok(response);

        } catch (RuntimeException e) {
            // Ejemplo: "Credenciales inválidas"
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }
}

