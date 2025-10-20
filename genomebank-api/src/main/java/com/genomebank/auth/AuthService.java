package com.genomebank.auth;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class AuthService {

    private final AuthenticationManager authManager;
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    public AuthService(AuthenticationManager authManager, JwtService jwtService, UserDetailsService userDetailsService) {
        this.authManager = authManager;
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
    }

    public AuthResponse login(AuthRequest request) {
        try {
            // 🔒 Autenticar usuario con el AuthenticationManager
            Authentication authentication = authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getUsername(),
                            request.getPassword()
                    )
            );

            // 🔎 Cargar detalles del usuario (ya validado)
            var user = userDetailsService.loadUserByUsername(request.getUsername());

            // 🪪 Generar JWT con claims opcionales (vacíos por ahora)
            String jwtToken = jwtService.generateToken(user.getUsername(), Map.of());

            // ✅ Retornar token al cliente
            return new AuthResponse(jwtToken);

        } catch (AuthenticationException e) {
            throw new RuntimeException("Credenciales inválidas");
        }
    }
}
