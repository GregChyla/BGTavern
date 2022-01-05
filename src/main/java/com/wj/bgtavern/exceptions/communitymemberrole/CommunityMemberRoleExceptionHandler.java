package com.wj.bgtavern.exceptions.communitymemberrole;

import com.wj.bgtavern.exceptions.ExceptionBody;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@ControllerAdvice
public class CommunityMemberRoleExceptionHandler {

    @ExceptionHandler(CommunityMemberRoleNotFoundException.class)
    public ResponseEntity<Object> handleCommunityMemberRoleNotFoundException(CommunityMemberRoleNotFoundException e) {
        ExceptionBody exception = new ExceptionBody(
                e.getMessage(),
                HttpStatus.CONFLICT,
                ZonedDateTime.now()
        );

        return new ResponseEntity<>(exception, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(CommunityMemberRoleAlreadyExistsException.class)
    public ResponseEntity<Object> handleCommunityMemberRoleAlreadyExistsException(CommunityMemberRoleAlreadyExistsException e) {
        ExceptionBody exception = new ExceptionBody(
                e.getMessage(),
                HttpStatus.FOUND,
                ZonedDateTime.now()
        );

        return new ResponseEntity<>(exception, HttpStatus.FOUND);
    }

}
