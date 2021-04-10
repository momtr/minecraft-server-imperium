package com.skyrocket.mcs.api.mapper;

import com.skyrocket.mcs.api.dto.users.UserProfileDto;
import com.skyrocket.mcs.api.model.user.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class UserProfileDtoMapper {

    public UserProfileDto map(User user) {
        return UserProfileDto.builder()
                .active(user.isActive())
                .firstName(user.getFirstName())
                .username(user.getUsername())
                .emoji(user.getEmoji())
                .birthdate(user.getBirthdate())
                .client(user.isClient())
                .description(user.getDescription())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .roles(user.getRoles())
                .build();
    }

}
