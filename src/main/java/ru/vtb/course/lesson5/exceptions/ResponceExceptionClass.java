package ru.vtb.course.lesson5.exceptions;

public class ResponceExceptionClass {
    private String message;
    private StackTraceElement[] stackTraceElements;

    public ResponceExceptionClass(String message, StackTraceElement[] stackTraceElements) {
        this.message = message;
        this.stackTraceElements = stackTraceElements;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public StackTraceElement[] getStackTraceElements() {
        return stackTraceElements;
    }

    public void setStackTraceElements(StackTraceElement[] stackTraceElements) {
        this.stackTraceElements = stackTraceElements;
    }
}
