package com.skyrocket.mcs.api.service;

import com.skyrocket.mcs.api.mapper.ServerDtoMapper;
import com.skyrocket.mcs.api.repository.ServerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServerService {

    @Autowired
    private ServerRepository serverRepository;

    @Autowired
    private ServerDtoMapper serverDtoMapper;

    @Autowired
    private UserService userService;

}
