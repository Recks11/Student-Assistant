package com.emmanuelirem.studentassistant.services.mapper;

import com.emmanuelirem.studentassistant.models.helper.Reference;

public interface ReferenceCreationService<T> {

    Reference<T> createReference(T t);
}
