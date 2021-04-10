package com.skyrocket.mcs.api.mapper;

import com.skyrocket.mcs.api.dto.forms.FormDataDto;
import com.skyrocket.mcs.api.model.domain.forms.FormData;
import org.springframework.stereotype.Component;

@Component
public class FormDataDtoMapper {

    public FormDataDto map(FormData formData) {
        return FormDataDto.builder()
                .createdAt(formData.getCreatedAt().getTime())
                .data(formData.getData())
                .datatype(formData.getDatatype())
                .formId(formData.getForm().getId())
                .id(formData.getId())
                .build();
    }

}
