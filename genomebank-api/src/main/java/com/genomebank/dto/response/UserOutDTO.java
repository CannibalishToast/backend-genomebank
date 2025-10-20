package com.genomebank.dto.response;

import com.genomebank.entities.Role;
import lombok.Data;

@Data
public class UserOutDTO {
    private Long id;
    private String name;
    private String email;
    private Role role;
}
