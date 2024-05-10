package com.safaricom.fairflowappmicroservice.models.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Occupation {
    SELF_EMPLOYED("SELF_EMPLOYED"),
    EMPLOYED("EMPLOYED"),
    STUDENT("STUDENT"),
    UNEMPLOYED("UNEMPLOYED");

    private final String value;

    Occupation(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    }
