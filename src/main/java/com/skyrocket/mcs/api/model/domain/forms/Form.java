package com.skyrocket.mcs.api.model.domain.forms;

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
@Table(name = "forms")
@Builder
@AllArgsConstructor
@EqualsAndHashCode
public class Form {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @Column(columnDefinition = "CHAR(32)")
    private String id;

    private Timestamp createdAt;

    @ManyToOne
    private User author;

    @NotNull
    private String name;

    @NotNull
    private String description;

    private String url;

    public Form() { }

}
