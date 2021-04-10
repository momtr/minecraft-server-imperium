package com.skyrocket.mcs.api.repository;

import com.skyrocket.mcs.api.model.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Page<User> findAllByActive(boolean active, Pageable pageable);
    Optional<User> findByUsername(String username);

    @Query("select u from User u where u not like :me and u.firstName like %:query% or u.username like %:query%")
    Page<User> searchUsersByQuery(String query, User me, Pageable pageable);

}
