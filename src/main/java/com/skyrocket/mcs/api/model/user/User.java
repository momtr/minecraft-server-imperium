package com.skyrocket.mcs.api.model.user;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Data
@Table(name = "users")
@Builder
@AllArgsConstructor
@EqualsAndHashCode
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private Long id;

    /**
     * Spring security
     */

    @NotNull
    private String username;

    @NotNull
    private String password;

    private boolean active;

    private String roles;

    /**
     * custom
     */

    private String firstName;

    private String lastName;

    private String description;

    private long birthdate;

    private boolean client;

    private String email;

    private String emoji;

    private Timestamp createdAt;

    public User() {}

}
