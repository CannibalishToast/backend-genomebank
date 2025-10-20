package com.genomebank.dto.auth;

import com.genomebank.dto.response.UserOutDTO;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponse {
    private String token;
    private String tokenType = "Bearer";
    private UserOutDTO user;
}
