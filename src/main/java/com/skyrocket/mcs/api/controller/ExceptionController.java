package com.skyrocket.mcs.api.controller;

import com.skyrocket.mcs.api.exception.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@ControllerAdvice
@Slf4j
public class ExceptionController {

    @ExceptionHandler(ApiException.class)
    public Map<String, String> handleApiException(ApiException ex) {
        log.info("handle API exception with message [{}] and code [{}] and object [{}]", ex.getMessage(), ex.getQcode(), ex.getObject());
        Map<String, String> map = new HashMap<>();
        map.put("message", ex.getMessage());
        map.put("qcode", ex.getQcode());
        map.put("object", ex.getObject());
        map.put("type", "API_ERROR");
        return map;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, Object> handleValidationExceptions(MethodArgumentNotValidException ex) {
        log.info("handle validation exception");
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        Map<String, Object> map = new HashMap<>();
        map.put("fields", errors);
        map.put("type", "VALIDATION_ERROR");
        return map;
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler({ UserAlreadyExistsException.class })
    public Map<String, String> handleExcpetion(UserAlreadyExistsException ex) {
        log.info("handle user already exists exception with message [{}]", ex.getMessage());
        Map<String, String> map = new HashMap<>();
        map.put("error", ex.getMessage());
        map.put("type", "USER_ALREADY_EXISTS");
        return map;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(UserDoesNotExistExcpetion.class)
    public Map<String, String> userDoesNotExist(UserDoesNotExistExcpetion ex) {
        log.info("handle user does not exist exception with message [{}]", ex.getMessage());
        Map<String, String> map = new HashMap<>();
        map.put("error", ex.getMessage());
        map.put("type", "USER_DOES_NOT_EXIST");
        return map;
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(UserNoRoleException.class)
    public Map<String, String> userNoRoleException(UserNoRoleException ex) {
        log.info("handle user no role exception with message [{}]", ex.getMessage());
        Map<String, String> map = new HashMap<>();
        map.put("error", ex.getMessage());
        map.put("type", "USER_NO_ROLE");
        map.put("role", ex.getRole());
        return map;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(FormNotFoundException.class)
    public Map<String, String> formNotFound(FormNotFoundException ex) {
        log.info("handle form not found exception with message [{}]", ex.getMessage());
        Map<String, String> map = new HashMap<>();
        map.put("error", ex.getMessage());
        map.put("type", "FORM_NOT_FOUND");
        return map;
    }

}
