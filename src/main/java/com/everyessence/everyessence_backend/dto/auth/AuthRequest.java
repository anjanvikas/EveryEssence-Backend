package com.everyessence.everyessence_backend.dto.auth;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthRequest {
    private String name;
    private String email;
    private String password;
}
