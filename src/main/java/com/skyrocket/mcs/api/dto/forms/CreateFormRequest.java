package com.skyrocket.mcs.api.dto.forms;

import lombok.Getter;

import javax.validation.constraints.NotEmpty;

@Getter
public class CreateFormRequest {

    @NotEmpty
    private String name;

    @NotEmpty
    private String description;

    private String url;

}
