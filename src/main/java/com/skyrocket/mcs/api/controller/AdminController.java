package com.skyrocket.mcs.api.controller;

import com.skyrocket.mcs.api.dto.users.ChangeRolesRequest;
import com.skyrocket.mcs.api.dto.users.UserProfileDto;
import com.skyrocket.mcs.api.exception.UserDoesNotExistExcpetion;
import com.skyrocket.mcs.api.exception.UserNoRoleException;
import com.skyrocket.mcs.api.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/admin")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Slf4j
public class AdminController {

    @Autowired
    private UserService userService;

    private final int PAGE_SIZE = 20;

    @GetMapping("/users")
    public ResponseEntity<Page<UserProfileDto>> getAllUsers(@RequestParam(name = "page", defaultValue = "0") int page) throws UserNoRoleException, UserDoesNotExistExcpetion {
        log.info("received request to get all users, page = [{}]", page);
        Pageable pageable = PageRequest.of(page, PAGE_SIZE, Sort.by(Sort.Direction.DESC, "createdAt"));
        return ResponseEntity.ok(userService.getAllUsers(pageable));
    }

    @PutMapping("/users/{username}/roles")
    public void changeUserRole(@PathVariable("username") String username, @Valid @RequestBody ChangeRolesRequest changeRolesRequest) throws UserNoRoleException, UserDoesNotExistExcpetion {
        log.info("received request to change roles of [{}] to [{}]", username, changeRolesRequest.getRoles());
        userService.changeRolesTo(username, changeRolesRequest.getRoles());
    }

}
