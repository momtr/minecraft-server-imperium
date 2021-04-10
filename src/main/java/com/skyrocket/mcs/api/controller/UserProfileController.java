package com.skyrocket.mcs.api.controller;

import com.skyrocket.mcs.api.dto.users.UserProfileDto;
import com.skyrocket.mcs.api.exception.*;
import com.skyrocket.mcs.api.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Slf4j
public class UserProfileController {

    @Autowired
    private UserService userService;


    private static final List<String> PROFILE_PICTURE_CONTENT_TYPES = Arrays.asList("image/png", "image/jpeg", "image/gif");

    private final int PAGE_SIZE = 5;

    @GetMapping("/me")
    public ResponseEntity<UserProfileDto> getMyProfile() throws ApiException {
        log.info("received request to get current user's profile");
        try {
            UserProfileDto userProfileDto = userService.getCurrentUserProfile();
            log.info("retrieved current user's profile; username [{}]", userProfileDto.getUsername());
            return ResponseEntity.ok(userProfileDto);
        } catch(UserDoesNotExistExcpetion e) {
            log.error("could not authenticate current user, system vulnerabilities discovered");
            throw new ApiException("could not get authenticated user", "#1");
        }
    }

}
