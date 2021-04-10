package com.skyrocket.mcs.api.service;

import com.skyrocket.mcs.api.model.user.SkyrocketUserDetails;
import com.skyrocket.mcs.api.model.user.User;
import com.skyrocket.mcs.api.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class HotspotsUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);
        user.orElseThrow(() -> new UsernameNotFoundException(String.format("User [%s] not found", username)));
        log.info("retrieved user [{}] from db with authorities [{}]", user.get().getUsername(), user.get().getRoles());
        SkyrocketUserDetails skyrocketUserDetails = user.map(SkyrocketUserDetails::new).get();
        return skyrocketUserDetails;
    }

}