package com.wj.bgtavern.exceptions.communitymember;

import com.wj.bgtavern.exceptions.ExceptionBody;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@ControllerAdvice
public class CommunityMemberExceptionHandler {

    @ExceptionHandler(CommunityMemberNotFoundException.class)
    public ResponseEntity<Object> handleCommunityMemberNotFoundException(CommunityMemberNotFoundException e) {
        ExceptionBody exception = new ExceptionBody(
                e.getMessage(),
                HttpStatus.CONFLICT,
                ZonedDateTime.now()
        );

        return new ResponseEntity<>(exception, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(CommunityMemberAlreadyExistsException.class)
    public ResponseEntity<Object> handleCommunityMemberAlreadyExistsException(CommunityMemberAlreadyExistsException e) {
        ExceptionBody exception = new ExceptionBody(
                e.getMessage(),
                HttpStatus.FOUND,
                ZonedDateTime.now()
        );

        return new ResponseEntity<>(exception, HttpStatus.FOUND);
    }

}
