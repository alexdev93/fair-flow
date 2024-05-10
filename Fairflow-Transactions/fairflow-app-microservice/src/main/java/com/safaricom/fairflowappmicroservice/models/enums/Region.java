package com.safaricom.fairflowappmicroservice.models.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Region {
    OROMIA("OROMIA"),
    TIGRAY("TIGRAY"),
    AMHARA("AMHARA"),
    SNNP("SNNP"),
    NA("NA");

    private final String value;

    Region(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    }
