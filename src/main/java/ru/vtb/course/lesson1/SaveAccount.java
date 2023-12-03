package ru.vtb.course.lesson1;

import java.util.Date;
import java.util.EnumMap;

public class SaveAccount {
    private final String name;
    private final EnumMap<Currency, Integer> currQuantity;
    private final Date date;

    public SaveAccount(String name, EnumMap<Currency, Integer> currQuantity) {
        this.name = name;
        this.date = new Date();
        this.currQuantity = new EnumMap<>(currQuantity);
    }

    public String getName() {
        return name;
    }

    public EnumMap<Currency, Integer> getCurrQuantity() {
        return new EnumMap<>(currQuantity);
    }

    public Date getDate() {
        return date;
    }

}
