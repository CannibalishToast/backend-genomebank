package com.genomebank.services.impl;

import com.genomebank.dto.auth.LoginRequest;
import com.genomebank.dto.auth.AuthResponse;
import com.genomebank.dto.in.UserInDTO;
import com.genomebank.dto.response.UserOutDTO;
import com.genomebank.entities.User;
import com.genomebank.repositories.UserRepository;
import com.genomebank.services.IAuthService;
import org.springframework.stereotype.Service;

@Service
public class AuthService implements IAuthService {

    private final UserRepository userRepository;

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserOutDTO register(UserInDTO userInDTO) {
        User user = new User();
        user.setName(userInDTO.getName());
        user.setEmail(userInDTO.getEmail());
        user.setPassword(userInDTO.getPassword());
        user.setRole(userInDTO.getRole());
        userRepository.save(user);

        return new UserOutDTO(user.getId(), user.getName(), user.getEmail(), user.getRole());
    }

    @Override
    public AuthResponse login(LoginRequest loginRequest) {
        // En una versión real aquí iría la validación del JWT
        return new AuthResponse("fake-jwt-token", "Login success");
    }
}
