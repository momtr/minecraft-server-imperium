package com.skyrocket.mcs.api.model.domain.forms;

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
@Table(name = "form_data")
@Builder
@AllArgsConstructor
@EqualsAndHashCode
public class FormData {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @Column(columnDefinition = "CHAR(32)")
    private String id;

    @ManyToOne
    private Form form;

    @NotNull
    private String data;

    @NotNull
    private String datatype;

    private Timestamp createdAt;

    public FormData() { }

}
