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

@RestControllerAdvice
public class ExceptionControllerAdvice {

    // обработка ошибок валидации @Valid
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponceExceptionClass validationErrorHandler(MethodArgumentNotValidException e) {
        FieldError fieldError = e.getBindingResult().getFieldError();
        assert fieldError != null;
        return new ResponceExceptionClass(fieldError.getDefaultMessage());
    }

    //обработка ошибок парсинга JSON
    @ExceptionHandler(JacksonException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponceExceptionClass handleException(JacksonException e) {
        String message = String.format("%s %s", LocalDateTime.now(), e.getMessage());
           return new ResponceExceptionClass(message);
    }

    //обработка ошибок поиска
    @ExceptionHandler(DuplicateException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponceExceptionClass handleDuplicateException(DuplicateException e) {
        String message = String.format("%s %s", LocalDateTime.now(), e.getMessage());
        return new ResponceExceptionClass(message);
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ResponceExceptionClass handleNotFoundException(NotFoundException e) {
        String message = String.format("%s %s", LocalDateTime.now(), e.getMessage());
        return new ResponceExceptionClass(message);
    }
    //обработка всех возможных ошибок
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ResponceExceptionClass handleAllException(Exception e) {
        String message = String.format("%s %s", LocalDateTime.now(), e.getMessage());
        return new ResponceExceptionClass(message);
    }

}
