package com.skyrocket.mcs.api.service;

import com.skyrocket.mcs.api.dto.forms.CreateFormRequest;
import com.skyrocket.mcs.api.dto.forms.FormDataDto;
import com.skyrocket.mcs.api.dto.forms.FormDto;
import com.skyrocket.mcs.api.dto.forms.UploadFormData;
import com.skyrocket.mcs.api.exception.FormNotFoundException;
import com.skyrocket.mcs.api.exception.UserDoesNotExistExcpetion;
import com.skyrocket.mcs.api.exception.UserNoRoleException;
import com.skyrocket.mcs.api.mapper.FormDataDtoMapper;
import com.skyrocket.mcs.api.mapper.FormDtoMapper;
import com.skyrocket.mcs.api.model.domain.forms.Form;
import com.skyrocket.mcs.api.model.domain.forms.FormData;
import com.skyrocket.mcs.api.model.user.User;
import com.skyrocket.mcs.api.repository.FormDataRepository;
import com.skyrocket.mcs.api.repository.FormRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Optional;

@Service
@Slf4j
public class FormService {

    @Autowired
    private UserService userService;

    @Autowired
    private FormRepository formRepository;

    @Autowired
    private FormDataRepository formDataRepository;

    @Autowired
    private FormDtoMapper formDtoMapper;

    @Autowired
    private FormDataDtoMapper formDataDtoMapper;

    private final String DEFAULT_DATATYPE = "JSON";

    public String createForm(CreateFormRequest createFormRequest) throws UserNoRoleException, UserDoesNotExistExcpetion {
        userService.checkUserEmployee();
        User user = userService.getCurrentUser();
        Form form = Form.builder()
                .author(user)
                .createdAt(new Timestamp(System.currentTimeMillis()))
                .description(createFormRequest.getDescription())
                .name(createFormRequest.getName())
                .url(createFormRequest.getUrl())
                .build();
        formRepository.save(form);
        return form.getId();
    }

    public void addFormData(String formId, UploadFormData uploadFormData) throws FormNotFoundException {
        Form form = findForm(formId);
        FormData formData = FormData.builder()
                .data(uploadFormData.getData())
                .datatype(DEFAULT_DATATYPE)
                .createdAt(new Timestamp(System.currentTimeMillis()))
                .form(form)
                .build();
        formDataRepository.save(formData);
    }

    public Page<FormDto> findForms(Pageable pageable) throws UserNoRoleException, UserDoesNotExistExcpetion {
        userService.checkUserEmployee();
        Page<Form> forms = formRepository.findAll(pageable);
        return forms.map(f -> formDtoMapper.map(f));
    }

    public Page<FormDataDto> findFormDataForForm(String formId, Pageable pageable) throws UserNoRoleException, UserDoesNotExistExcpetion, FormNotFoundException {
        userService.checkUserEmployee();
        Form form = findForm(formId);
        Page<FormData> formData = formDataRepository.findAllByForm(form, pageable);
        return formData.map(d -> formDataDtoMapper.map(d));
    }

    public Form findForm(String id) throws FormNotFoundException {
        Optional<Form> form = formRepository.findById(id);
        form.orElseThrow(() -> new FormNotFoundException(id));
        return form.get();
    }

}
