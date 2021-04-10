package com.skyrocket.mcs.api.dto.forms;

import com.skyrocket.mcs.api.dto.users.UserProfileDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class FormDto {
    private String id;
    private long createdAt;
    private String name;
    private String description;
    private String url;
    private UserProfileDto author;
}
