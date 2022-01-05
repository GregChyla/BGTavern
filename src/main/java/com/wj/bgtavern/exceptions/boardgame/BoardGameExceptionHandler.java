package com.wj.bgtavern.exceptions.boardgame;

import com.wj.bgtavern.exceptions.ExceptionBody;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@ControllerAdvice
public class BoardGameExceptionHandler {

    @ExceptionHandler(BoardGameNotFoundException.class)
    public ResponseEntity<Object> handleBoardGameNotFoundException(BoardGameNotFoundException e) {
        ExceptionBody exception = new ExceptionBody(
                e.getMessage(),
                HttpStatus.CONFLICT,
                ZonedDateTime.now()
        );

        return new ResponseEntity<>(exception, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(BoardGameAlreadyExistsException.class)
    public ResponseEntity<Object> handleBoardGameAlreadyExistsException(BoardGameAlreadyExistsException e) {
        ExceptionBody exception = new ExceptionBody(
                e.getMessage(),
                HttpStatus.FOUND,
                ZonedDateTime.now()
        );

        return new ResponseEntity<>(exception, HttpStatus.FOUND);
    }

}
