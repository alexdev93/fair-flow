package com.safaricom.fairflowappmicroservice.models.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Gender {
    M("M"),
    F("F");

    private final String value;

    Gender(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }
}
