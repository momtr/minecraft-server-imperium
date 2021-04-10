package com.skyrocket.mcs.api.mapper;

import com.skyrocket.mcs.api.dto.forms.FormDto;
import com.skyrocket.mcs.api.model.domain.forms.Form;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FormDtoMapper {

    @Autowired
    private UserProfileDtoMapper userProfileDtoMapper;

    public FormDto map(Form form) {
        return FormDto.builder()
                .author(userProfileDtoMapper.map(form.getAuthor()))
                .createdAt(form.getCreatedAt().getTime())
                .description(form.getDescription())
                .id(form.getId())
                .name(form.getName())
                .url(form.getUrl())
                .build();
    }

}
