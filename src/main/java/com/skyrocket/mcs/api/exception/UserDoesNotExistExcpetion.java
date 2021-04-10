package com.skyrocket.mcs.api.exception;

public class UserDoesNotExistExcpetion extends Exception {

    public UserDoesNotExistExcpetion(String username) {
        super(String.format("wrong username or password or user [%s] does not exist", username));
    }

}
