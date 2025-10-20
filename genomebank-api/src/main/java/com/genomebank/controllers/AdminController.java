package com.genomebank.controllers;

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
    public ResponseEntity<?> changeUserRole(@PathVariable Long id, @RequestParam Role newRole) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));

        user.setRole(newRole);
        userRepository.save(user);

        return ResponseEntity.ok("Role updated to: " + newRole);
    }


    @PutMapping("/user/email")
    public ResponseEntity<?> changeUserRoleByEmail(@RequestParam String email, @RequestParam Role newRole) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found with email: " + email));

        user.setRole(newRole);
        userRepository.save(user);

        return ResponseEntity.ok("Role updated to: " + newRole);
    }
}
