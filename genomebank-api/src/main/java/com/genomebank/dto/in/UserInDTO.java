package com.genomebank.dto.in;

import com.genomebank.entities.Role;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInDTO {
    @NotBlank
    private String name;

    @Email
    @NotBlank
    private String email;

    @NotBlank
    @Size(min = 6, message = "Password must have at least 6 characters")
    private String password;

    @NotNull
    private Role role; // ADMIN o USER
}
