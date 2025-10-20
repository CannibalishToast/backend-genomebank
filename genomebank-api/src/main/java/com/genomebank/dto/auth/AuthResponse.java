package com.genomebank.dto.auth;

import com.genomebank.dto.response.UserOutDTO;
import lombok.Data;

@Data
public class AuthResponse {
    private String token;
    private String tokenType = "Bearer";
    private UserOutDTO user;
}
