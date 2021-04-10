package com.skyrocket.mcs.api.dto.auth;

import lombok.Getter;

@Getter
public class AuthenticationRequest {
    private String username;
    private String password;
}
