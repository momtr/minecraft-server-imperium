package com.skyrocket.mcs.api.util;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Component;

@Component
public class TokenGeneratorUtil {

    private final String POSSIBLE_TOKEN_CHARS = "ABCEEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

    public String generateToken(int length) {
        return RandomStringUtils.random(length, POSSIBLE_TOKEN_CHARS);
    }

}
