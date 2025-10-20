package com.genomebank.auth;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {
        try {
            // Delegar autenticación completamente al AuthService
            AuthResponse response = authService.login(request);
            return ResponseEntity.ok(response);

        } catch (AuthenticationException e) {
            return ResponseEntity.status(401).body(new AuthResponse("Credenciales inválidas"));
        } catch (RuntimeException e) {
            return ResponseEntity.status(400).body(new AuthResponse(e.getMessage()));
        }
    }
}
