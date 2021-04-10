package com.skyrocket.mcs.api.controller;

import com.skyrocket.mcs.api.dto.forms.*;
import com.skyrocket.mcs.api.exception.FormNotFoundException;
import com.skyrocket.mcs.api.exception.UserDoesNotExistExcpetion;
import com.skyrocket.mcs.api.exception.UserNoRoleException;
import com.skyrocket.mcs.api.service.FormService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/forms")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Slf4j
public class FormController {

    @Autowired
    private FormService formService;

    private final int PAGE_SIZE = 20;

    @PostMapping("")
    public ResponseEntity<CreateFormResponse> createForm(@Valid @RequestBody CreateFormRequest createFormRequest) throws UserNoRoleException, UserDoesNotExistExcpetion {
        log.info("received request to create form [{}]", createFormRequest.getName());
        String id = formService.createForm(createFormRequest);
        return ResponseEntity.ok(new CreateFormResponse(id));
    }

    @PostMapping("/public/{id}/data")
    public void addFormData(@PathVariable("id") String id, @Valid @RequestBody UploadFormData uploadFormData) throws FormNotFoundException {
        log.info("received request to upload form data to form with id [{}]", id);
        formService.addFormData(id, uploadFormData);
    }

    @GetMapping("")
    public ResponseEntity<Page<FormDto>> getForms(@RequestParam(name = "page", defaultValue = "0") int page) throws UserNoRoleException, UserDoesNotExistExcpetion {
        log.info("received request to get forms, page = [{}]", page);
        Pageable pageable = PageRequest.of(page, PAGE_SIZE, Sort.by(Sort.Direction.DESC, "createdAt"));
        return ResponseEntity.ok(formService.findForms(pageable));
    }

    @GetMapping("/{id}/data")
    public ResponseEntity<Page<FormDataDto>> getForms(@PathVariable("id") String id, @RequestParam(name = "page", defaultValue = "0") int page) throws UserDoesNotExistExcpetion, UserNoRoleException, FormNotFoundException {
        log.info("received requets to get form data form form [{}], page = [{}]", id, page);
        Pageable pageable = PageRequest.of(page, PAGE_SIZE, Sort.by(Sort.Direction.DESC, "createdAt"));
        return ResponseEntity.ok(formService.findFormDataForForm(id, pageable));
    }

}
