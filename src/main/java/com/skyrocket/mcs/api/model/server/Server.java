package com.skyrocket.mcs.api.model.server;

import com.skyrocket.mcs.api.model.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Entity
@Data
@Table(name = "servers")
@Builder
@AllArgsConstructor
@EqualsAndHashCode
public class Server {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @Column(columnDefinition = "CHAR(32)")
    private Long id;

    @NotNull
    private String name;

    @Enumerated(EnumType.STRING)
    private ServerType serverType;

    private double ram;

    private int port;

    private String domain;

    private boolean running;

    @ManyToOne
    private User creator;

    private Timestamp createdAt;

    private Timestamp onSince;

    private String opIngameName;

    public Server() { }

}
