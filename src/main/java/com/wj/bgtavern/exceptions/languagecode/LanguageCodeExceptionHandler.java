package com.wj.bgtavern.exceptions.languagecode;

import com.wj.bgtavern.exceptions.ExceptionBody;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@ControllerAdvice
public class LanguageCodeExceptionHandler {

    @ExceptionHandler(LanguageCodeNotFoundException.class)
    public ResponseEntity<Object> handleLanguageCodeNotFoundException(LanguageCodeNotFoundException e) {
        ExceptionBody exception = new ExceptionBody(
                e.getMessage(),
                HttpStatus.CONFLICT,
                ZonedDateTime.now()
        );

        return new ResponseEntity<>(exception, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(LanguageCodeAlreadyExistsException.class)
    public ResponseEntity<Object> handleLanguageCodeExistsException(LanguageCodeAlreadyExistsException e) {
        ExceptionBody exception = new ExceptionBody(
                e.getMessage(),
                HttpStatus.FOUND,
                ZonedDateTime.now()
        );

        return new ResponseEntity<>(exception, HttpStatus.FOUND);
    }

}
