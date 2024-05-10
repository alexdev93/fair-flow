package com.safaricom.fairflowappmicroservice.models.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum MartialStatus {
    SINGLE("SINGLE"),
    MARRIED("MARRIED"),
    WIDOWED("WIDOWED"),
    DIVORCED("DIVORCED");
    private final String value;

    MartialStatus(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

}
