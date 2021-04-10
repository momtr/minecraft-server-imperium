package com.skyrocket.mcs.api.controller;

import com.skyrocket.mcs.api.dto.auth.AuthenticationResponse;
import com.skyrocket.mcs.api.dto.users.CreateUserRequest;
import com.skyrocket.mcs.api.exception.UserAlreadyExistsException;
import com.skyrocket.mcs.api.exception.UserDoesNotExistExcpetion;
import com.skyrocket.mcs.api.model.user.User;
import com.skyrocket.mcs.api.service.HotspotsUserDetailsService;
import com.skyrocket.mcs.api.service.UserService;
import com.skyrocket.mcs.api.dto.auth.AuthenticationRequest;
import com.skyrocket.mcs.api.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Slf4j
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private HotspotsUserDetailsService hotspotsUserDetailsService;

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/signin")
    public ResponseEntity<AuthenticationResponse> signin(@Valid @RequestBody AuthenticationRequest authenticationRequest) throws Exception {
        log.info("received request to authenticate user with username [{}]", authenticationRequest.getUsername());
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()));
        } catch (BadCredentialsException e) {
            log.error("could not log user in [{}]", e.getLocalizedMessage());
            throw new UserDoesNotExistExcpetion(authenticationRequest.getUsername());
        }
        final UserDetails userDetails = hotspotsUserDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        final String jwt = jwtUtil.generateToken(userDetails);
        log.info("generated jwt for user");
        User user = userService.findUser(userDetails.getUsername());
        return ResponseEntity.ok(AuthenticationResponse.builder()
                .jwt(jwt)
                .roles(user.getRoles())
                .validUntil(System.currentTimeMillis() + 1000*60*60*24)
                .build());
    }

    @PostMapping("/signup")
    public void signup(@Valid @RequestBody CreateUserRequest createProfileRequest) throws UserAlreadyExistsException, UserDoesNotExistExcpetion {
        log.info("received request to sign up user with username [{}] and firstName [{}]", createProfileRequest.getUsername(), createProfileRequest.getFirstName());
        userService.createUser(createProfileRequest);
    }

}
