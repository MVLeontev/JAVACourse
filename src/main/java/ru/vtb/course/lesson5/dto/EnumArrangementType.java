package ru.vtb.course.lesson5.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum EnumArrangementType {
    @JsonProperty("НСО")
    NSO("НСО"),
    @JsonProperty("ЕЖО")
    EZHO("ЕЖО"),
    @JsonProperty("СМО")
    SMO("СМО"),
    @JsonProperty("ДБДС")
    DBDS("ДБДС");

    private final String name;

    EnumArrangementType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

