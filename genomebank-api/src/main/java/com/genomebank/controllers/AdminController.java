package com.genomebank.controllers;

import com.genomebank.dto.response.UserOutDTO;
import com.genomebank.entities.Role;
import com.genomebank.entities.User;
import com.genomebank.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final UserRepository userRepository;

    @PatchMapping("/user/{id}/role")
    public ResponseEntity<UserOutDTO> changeUserRole(@PathVariable Long id, @RequestParam Role newRole) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));

        user.setRole(newRole);
        userRepository.save(user);

        UserOutDTO userOutDTO = new UserOutDTO(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getRole()
        );

        return ResponseEntity.ok(userOutDTO);
    }

    @PatchMapping("/user/email")
    public ResponseEntity<UserOutDTO> changeUserRoleByEmail(@RequestParam String email, @RequestParam Role newRole) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found with email: " + email));

        user.setRole(newRole);
        userRepository.save(user);

        UserOutDTO userOutDTO = new UserOutDTO(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getRole()
        );

        return ResponseEntity.ok(userOutDTO);
    }
}

