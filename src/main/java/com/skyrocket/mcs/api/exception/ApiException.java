package com.skyrocket.mcs.api.exception;

import lombok.Getter;

@Getter
public class ApiException extends Exception {

    private String qcode;
    private String object;

    public ApiException(String message) {
        super(message);
    }

    public ApiException(String message, String qcode) {
        super(message);
    }

    public ApiException(String message, String qcode, String object) {
        super(message);
        this.qcode = qcode;
        this.object = object;
    }

}
