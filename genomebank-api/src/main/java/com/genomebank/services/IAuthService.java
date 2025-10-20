package com.genomebank.services;

import com.genomebank.dto.auth.LoginRequest;
import com.genomebank.dto.auth.AuthResponse;
import com.genomebank.dto.in.UserInDTO;
import com.genomebank.dto.response.UserOutDTO;

public interface IAuthService {
    UserOutDTO register(UserInDTO userInDTO);
    AuthResponse login(LoginRequest loginRequest);
}
