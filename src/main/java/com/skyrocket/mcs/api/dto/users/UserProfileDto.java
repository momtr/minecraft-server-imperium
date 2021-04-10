package com.skyrocket.mcs.api.dto.users;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class UserProfileDto {
    private String username;
    private String firstName;
    private String lastName;
    private long birthdate;
    private String description;
    private boolean client;
    private boolean active;
    private String emoji;
    private String email;
    private String roles;
}
