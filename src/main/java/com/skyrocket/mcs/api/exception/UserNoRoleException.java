package com.skyrocket.mcs.api.exception;

import lombok.Getter;

@Getter
public class UserNoRoleException extends Exception {

    private String role;

    public UserNoRoleException(String username, String role) {
        super(String.format("user [%s] does not have role [%s], which is required for this operation", username, role));
        this.role = role;
    }

}
