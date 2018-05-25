package com.emmanuelirem.studentassistant.services.mapper.impl;

import com.emmanuelirem.studentassistant.models.helper.Reference;
import com.emmanuelirem.studentassistant.services.mapper.ReferenceCreationService;
import org.springframework.stereotype.Component;

@Component
public class ReferenceCreationServiceImpl implements ReferenceCreationService {

    @Override
    public Reference createReference(Object o) {
        return new Reference<>(o);
    }
}
