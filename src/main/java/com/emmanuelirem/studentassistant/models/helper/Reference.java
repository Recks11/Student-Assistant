package com.emmanuelirem.studentassistant.models.helper;


import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Transient;

import javax.validation.constraints.NotNull;

public class Reference<T> {

    private String id;

    @Transient
    @JsonProperty("name")
    private String referenceName;

    public Reference() {
    }

    public Reference(String id, String name) {
        this.id = id;
        this.referenceName = name;
    }

    public Reference(@NotNull T o) {
        Reference obj = (Reference) o;
        this.id = obj.getId();
        this.referenceName = obj.getName();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return referenceName;
    }

    public void setName(String name) {
        this.referenceName = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Reference<?> reference = (Reference<?>) o;

        if (!id.equals(reference.id)) return false;
        return referenceName.equals(reference.referenceName);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + referenceName.hashCode();
        return result;
    }
}
