package com.skyrocket.mcs.api.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class AuthenticationResponse {
    private String jwt;
    private long validUntil;
    private String roles;
}
