package com.skyrocket.mcs.api.dto.users;

import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Getter
public class ChangeRolesRequest {

    @NotBlank(message = "Roles is mandatory")
    @NotEmpty
    private String roles;

}
