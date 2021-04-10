package com.skyrocket.mcs.api.repository;

import com.skyrocket.mcs.api.model.server.Server;
import com.skyrocket.mcs.api.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServerRepository extends JpaRepository<Server, String> {
    List<Server> findAllByCreator(User user);
}
