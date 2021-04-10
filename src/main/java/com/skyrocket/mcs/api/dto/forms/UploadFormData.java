package com.skyrocket.mcs.api.dto.forms;

import lombok.Getter;

import javax.validation.constraints.NotEmpty;

@Getter
public class UploadFormData {

    @NotEmpty
    private String data;

}
