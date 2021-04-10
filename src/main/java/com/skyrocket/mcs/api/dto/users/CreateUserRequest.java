package com.skyrocket.mcs.api.dto.users;

import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
public class CreateUserRequest {

    @NotBlank(message = "Username is mandatory")
    @NotEmpty
    @Size(min = 2, max = 50)
    private String username;

    @NotBlank(message = "Password is mandatory")
    @NotEmpty
    private String password;

    @NotBlank(message = "Firstname is mandatory")
    @Size(min = 2, max = 30)
    private String firstName;

    @NotBlank(message = "Lastname is mandatory")
    @Size(min = 2, max = 50)
    private String lastName;

    @NotBlank(message = "Emoji is required")
    @Size(min = 1, max = 10)
    private String emoji;

    @NotBlank(message = "Email is required")
    @Size(min = 1, max = 100)
    private String email;

    private long birthdate;

}
