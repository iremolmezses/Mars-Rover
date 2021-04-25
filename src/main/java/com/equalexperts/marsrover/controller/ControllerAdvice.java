package com.equalexperts.marsrover.controller;

import lombok.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdvice {
    @ExceptionHandler({HttpMessageNotReadableException.class, MethodArgumentNotValidException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public final ErrorResponse handleBadRequest(){
        return ErrorResponse.of(HttpStatus.BAD_REQUEST.value(),
                       "Please supply a valid input");
    }
    @ExceptionHandler({IllegalArgumentException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public final ErrorResponse handleUnsupportedCommand(){
        return ErrorResponse.of(HttpStatus.BAD_REQUEST.value(),
                       "An unsupported command received");
    }
    @ExceptionHandler({HttpMediaTypeNotSupportedException.class})
    @ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
    public final ErrorResponse handleUnsupportedMedia(){
        return ErrorResponse.of(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value(),
                       "Unsupported media type, please send the command as text/plain");
    }

    @Value(staticConstructor = "of")
    public static class ErrorResponse {
        int status;
        String message;
    }
}
