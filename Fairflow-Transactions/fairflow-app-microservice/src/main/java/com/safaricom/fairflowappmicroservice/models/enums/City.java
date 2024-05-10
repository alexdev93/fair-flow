package com.safaricom.fairflowappmicroservice.models.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum City {
    ADDIS_ABABA("Addis Ababa"),
    DIRE_DAWA("Dire Dawa");

    private final String value;

    City(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }
}
