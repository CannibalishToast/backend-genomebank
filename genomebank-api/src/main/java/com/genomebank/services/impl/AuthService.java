package com.genomebank.services.impl;

import com.genomebank.dto.auth.AuthResponse;
import com.genomebank.dto.auth.LoginRequest;
import com.genomebank.dto.in.UserInDTO;
import com.genomebank.dto.response.UserOutDTO;
import com.genomebank.entities.User;
import com.genomebank.repositories.UserRepository;
import com.genomebank.services.IAuthService;
import com.genomebank.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService implements IAuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Override
    public AuthResponse register(UserInDTO userInDTO) {
        // Validar si el correo ya existe
        if (userRepository.findByEmail(userInDTO.getEmail()).isPresent()) {
            throw new RuntimeException("El email ya esta registrado");
        }

        // Crear el nuevo usuario
        User user = new User(
                null,
                userInDTO.getName(),
                userInDTO.getEmail(),
                passwordEncoder.encode(userInDTO.getPassword()),
                userInDTO.getRole()
        );

        userRepository.save(user);

        // Crear DTO de salida
        UserOutDTO userOutDTO = new UserOutDTO(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getRole()
        );

        // Generar token JWT
        String token = jwtService.generateToken(user);

        return new AuthResponse(token, "Bearer", userOutDTO);
    }

    @Override
    public AuthResponse login(LoginRequest loginRequest) {
        // Buscar usuario por email
        User user = userRepository.findByEmail(loginRequest.getEmail())
                .orElseThrow(() -> new RuntimeException("Credenciales invalidas"));

        // Verificar contraseña
        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            throw new RuntimeException("Credenciales invalidas");
        }

        // Crear DTO de salida
        UserOutDTO userOutDTO = new UserOutDTO(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getRole()
        );

        // Generar token JWT
        String token = jwtService.generateToken(user);

        return new AuthResponse(token, "Bearer", userOutDTO);
    }
}
