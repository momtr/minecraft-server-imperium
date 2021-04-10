package com.skyrocket.mcs.api.exception;

public class FormNotFoundException extends Exception {

    public FormNotFoundException(String id) {
        super(String.format("form with id [%s] not found", id));
    }

}
