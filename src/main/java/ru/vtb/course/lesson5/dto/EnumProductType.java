package ru.vtb.course.lesson5.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum EnumProductType {
    @JsonProperty("НСО")
    NSO("НСО"),
    @JsonProperty("СМО")
    SMO("СМО"),
    @JsonProperty("ЕЖО")
    EZHO("ЕЖО"),
    @JsonProperty("ДБДС")
    DBDS("ДБДС"),
    @JsonProperty("договор")
    DOG("договор");

    private final String name;

    EnumProductType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
