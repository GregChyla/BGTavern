package com.wj.bgtavern.exceptions.boardgameteammember;

import com.wj.bgtavern.exceptions.ExceptionBody;
import com.wj.bgtavern.exceptions.boardgame.BoardGameNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZonedDateTime;

@ControllerAdvice
public class BoardGameTeamMemberExceptionHandler {


    @ExceptionHandler(BoardGameTeamMemberNotFoundException.class)
    public ResponseEntity<Object> handleBoardGameTeamMemberNotFoundException(BoardGameTeamMemberNotFoundException e) {
        ExceptionBody exception = new ExceptionBody(
                e.getMessage(),
                HttpStatus.NOT_FOUND,
                ZonedDateTime.now()
        );

        return new ResponseEntity<>(exception, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BoardGameTeamMemberAlreadyExistsException.class)
    public ResponseEntity<Object> handleBoardGameTeamMemberAlreadyExistsException(BoardGameTeamMemberAlreadyExistsException e) {
        ExceptionBody exception = new ExceptionBody(
                e.getMessage(),
                HttpStatus.CONFLICT,
                ZonedDateTime.now()
        );

        return new ResponseEntity<>(exception, HttpStatus.CONFLICT);
    }

}
