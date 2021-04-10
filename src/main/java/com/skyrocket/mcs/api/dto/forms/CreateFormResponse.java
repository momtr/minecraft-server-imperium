package com.skyrocket.mcs.api.dto.forms;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class CreateFormResponse {
    private String id;
}
