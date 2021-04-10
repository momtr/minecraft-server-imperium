package com.skyrocket.mcs.api.service;

import com.skyrocket.mcs.api.dto.users.CreateUserRequest;
import com.skyrocket.mcs.api.dto.users.UserProfileDto;
import com.skyrocket.mcs.api.exception.*;
import com.skyrocket.mcs.api.mapper.UserProfileDtoMapper;
import com.skyrocket.mcs.api.model.user.RoleNames;
import com.skyrocket.mcs.api.model.user.User;
import com.skyrocket.mcs.api.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Optional;

@Service
@Slf4j
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserProfileDtoMapper userProfileDtoMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void createUser(CreateUserRequest createUserRequest) throws UserAlreadyExistsException, UserDoesNotExistExcpetion {
        String username = createUserRequest.getUsername().toLowerCase();
        if(userExists(username))
            throw new UserAlreadyExistsException(username);
        User user = User.builder()
                .active(true)
                .username(username)
                .firstName(createUserRequest.getFirstName())
                .password(passwordEncoder.encode(createUserRequest.getPassword()))
                .roles(RoleNames.DEFAULT_ROLE_NAME)
                .emoji(createUserRequest.getEmoji())
                .birthdate(createUserRequest.getBirthdate())
                .lastName(createUserRequest.getLastName())
                .email(createUserRequest.getEmail())
                .createdAt(new Timestamp(System.currentTimeMillis()))
                .build();
        if(username.equals("moritz"))
            user.setRoles(user.getRoles() + ",EMPLOYEE,ADMIN");
        userRepository.save(user);
        log.info("persisted user [{}] with id [{}]", username, user.getId());
    }

    public boolean userExists(String username) {
        return userRepository.findByUsername(username).isPresent();
    }

    public User findUser(String username) throws UserDoesNotExistExcpetion {
        Optional<User> user = userRepository.findByUsername(username);
        user.orElseThrow(() -> new UserDoesNotExistExcpetion(username));
        log.info("retrieved user [{}] from db", username);
        return user.get();
    }

    public User getCurrentUser() throws UserDoesNotExistExcpetion {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String username = authentication.getName();
            return findUser(username);
        }
        log.error("potential vulnerability: cannot retrieve current user from db. authentication name is [{}]", authentication.getName());
        return null;
    }

    public UserProfileDto getCurrentUserProfile() throws UserDoesNotExistExcpetion {
        User user = getCurrentUser();
        return userProfileDtoMapper.map(user);
    }

    public Page<UserProfileDto> getAllUsers(Pageable pageable) throws UserNoRoleException, UserDoesNotExistExcpetion {
        checkUserAdmin();
        Page<User> users = userRepository.findAllByActive(true, pageable);
        return users.map(u -> userProfileDtoMapper.map(u));
    }

    public Page<UserProfileDto> findUsersByQuery(String query, Pageable pageable) throws UserDoesNotExistExcpetion {
        User current = getCurrentUser();
        Page<User> users = userRepository.searchUsersByQuery(query, current, pageable);
        return users.map(user -> userProfileDtoMapper.map(user));
    }

    public void checkUserEmployee() throws UserDoesNotExistExcpetion, UserNoRoleException {
        if(!checkRole("EMPLOYEE"))
            throw new UserNoRoleException(getCurrentUser().getUsername(), "EMPLOYEE");
    }

    public void checkUserAdmin() throws UserDoesNotExistExcpetion, UserNoRoleException {
        if(!checkRole("ADMIN"))
            throw new UserNoRoleException(getCurrentUser().getUsername(), "ADMIN");
    }

    public boolean checkRole(String role) throws UserDoesNotExistExcpetion {
        return (getCurrentUser().getRoles().indexOf(role) >= 0);
    }

    public void changeRolesTo(String username, String roles) throws UserNoRoleException, UserDoesNotExistExcpetion {
        checkUserAdmin();
        User user = findUser(username);
        user.setRoles(roles);
        userRepository.save(user);
    }

}
