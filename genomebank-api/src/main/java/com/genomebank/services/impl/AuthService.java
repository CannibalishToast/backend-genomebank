package com.genomebank.services.impl;

import com.genomebank.dto.auth.AuthResponse;
import com.genomebank.dto.auth.LoginRequest;
import com.genomebank.dto.in.UserInDTO;
import com.genomebank.dto.response.UserOutDTO;
import com.genomebank.entities.User;
import com.genomebank.repositories.UserRepository;
import com.genomebank.auth.JwtService;
import com.genomebank.services.IAuthService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthService implements IAuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authManager;

    @Override
    public AuthResponse register(UserInDTO userInDTO) {
        // 🧩 Verificar duplicado de email
        if (userRepository.findByUsername(userInDTO.getEmail()).isPresent()) {
            throw new RuntimeException("El email ya está registrado");
        }

        // 🧠 Crear nuevo usuario
        User user = new User(
                null,
                userInDTO.getName(),
                userInDTO.getEmail(),
                passwordEncoder.encode(userInDTO.getPassword()),
                userInDTO.getRole()
        );

        userRepository.save(user);

        // 🪪 Generar token JWT
        String token = jwtService.generateToken(user);

        // 📦 DTO de salida
        UserOutDTO userOutDTO = new UserOutDTO(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getRole()
        );

        return new AuthResponse(token, "Bearer", userOutDTO);
    }

    @Override
    public AuthResponse login(LoginRequest loginRequest) {
        try {
            // 🔒 Autenticar usando el AuthenticationManager de Spring
            authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getEmail(),
                            loginRequest.getPassword()
                    )
            );
        } catch (AuthenticationException e) {
            throw new RuntimeException("Credenciales inválidas");
        }

        // 🔍 Recuperar usuario autenticado
        User user = userRepository.findByUsername(loginRequest.getEmail())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        // 🪪 Generar JWT
        String token = jwtService.generateToken(user);

        // 📦 Crear DTO de salida
        UserOutDTO userOutDTO = new UserOutDTO(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getRole()
        );

        return new AuthResponse(token, "Bearer", userOutDTO);
    }
}

