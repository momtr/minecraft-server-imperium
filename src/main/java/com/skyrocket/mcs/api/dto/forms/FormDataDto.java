package com.skyrocket.mcs.api.dto.forms;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class FormDataDto {
    private String id;
    private String formId;
    private String datatype;
    private String data;
    private long createdAt;
}
