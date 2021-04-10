package com.skyrocket.mcs.api.mapper;

import com.skyrocket.mcs.api.dto.servers.ServerDto;
import com.skyrocket.mcs.api.model.server.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ServerDtoMapper {

    @Autowired
    private UserProfileDtoMapper userProfileDtoMapper;

    public ServerDto map(Server server) {
        return ServerDto.builder()
                .createdAt(server.getCreatedAt().getTime())
                .creator(userProfileDtoMapper.map(server.getCreator()))
                .domain(server.getDomain())
                .name(server.getName())
                .id(server.getId().longValue())
                .onSince(server.getOnSince().getTime())
                .port(server.getPort())
                .ram(server.getRam())
                .running(server.isRunning())
                .serverType(server.getServerType())
                .build();
    }

}
