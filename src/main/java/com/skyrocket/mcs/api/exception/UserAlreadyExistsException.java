package com.skyrocket.mcs.api.exception;

public class UserAlreadyExistsException extends Exception {

    public UserAlreadyExistsException(String username) {
        super(String.format("user [%s] already exists", username));
    }

}
