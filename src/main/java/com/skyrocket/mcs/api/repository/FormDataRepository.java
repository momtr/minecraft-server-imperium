package com.skyrocket.mcs.api.repository;

import com.skyrocket.mcs.api.model.domain.forms.Form;
import com.skyrocket.mcs.api.model.domain.forms.FormData;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FormDataRepository extends JpaRepository<FormData, String> {
    Page<FormData> findAll(Pageable pageable);
    Page<FormData> findAllByForm(Form form, Pageable pageable);
}
