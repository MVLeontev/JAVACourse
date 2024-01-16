package ru.vtb.course.lesson5.exceptions;

public class ResponceExceptionClass {
    private String message;

    public ResponceExceptionClass(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
