package com.skyrocket.mcs.api.dto.servers;

import com.skyrocket.mcs.api.dto.users.UserProfileDto;
import com.skyrocket.mcs.api.model.server.ServerType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class ServerDto {
    private Long id;
    private String name;
    private ServerType serverType;
    private double ram;
    private int port;
    private String domain;
    private boolean running;
    private UserProfileDto creator;
    private long createdAt;
    private long onSince;
}
