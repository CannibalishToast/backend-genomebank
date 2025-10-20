package com.genomebank.services;

import com.genomebank.dto.auth.AuthResponse;
import com.genomebank.dto.auth.LoginRequest;
import com.genomebank.dto.in.UserInDTO;

public interface IAuthService {

    AuthResponse register(UserInDTO userInDTO);

    AuthResponse login(LoginRequest loginRequest);
}
