package ru.vtb.course.lesson5.exceptions;

import com.fasterxml.jackson.core.JacksonException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.Arrays;

@RestControllerAdvice
public class ExceptionControllerAdvice {

    // обработка ошибок валидации @Valid
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponceExceptionClass validationErrorHandler(MethodArgumentNotValidException e) {
        FieldError fieldError = e.getBindingResult().getFieldError();
        StackTraceElement[] stack = e.getStackTrace();
        assert fieldError != null;
        return new ResponceExceptionClass(fieldError.getDefaultMessage(), stack);
    }

    //обработка ошибок парсинга JSON
    @ExceptionHandler(JacksonException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponceExceptionClass handleException(JacksonException e) {

        StackTraceElement[] stack = e.getStackTrace();
        String message = e.getMessage();
           return new ResponceExceptionClass(message,stack);
    }

    //обработка ошибок поиска
    @ExceptionHandler(DuplicateException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponceExceptionClass handleDuplicateException(DuplicateException e) {
        String message = e.getMessage();
        StackTraceElement[] stack = e.getStackTrace();
        return new ResponceExceptionClass(message, stack);
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponceExceptionClass handleNotFoundException(NotFoundException e) {
        String message = e.getMessage();
        StackTraceElement[] stack = e.getStackTrace();
        return new ResponceExceptionClass(message, stack);
    }
    //обработка всех возможных ошибок
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponceExceptionClass handleAllException(Exception e) {
        String message = e.getMessage();
        StackTraceElement[] stack = e.getStackTrace();
        return new ResponceExceptionClass(message, stack);
    }

}
