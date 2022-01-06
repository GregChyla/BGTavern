package com.wj.bgtavern.exceptions.boardgamedescription;

import com.wj.bgtavern.exceptions.ExceptionBody;
import com.wj.bgtavern.exceptions.boardgame.BoardGameAlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZonedDateTime;

@ControllerAdvice
public class BoardGameDescriptionExceptionHandler {

    @ExceptionHandler(BoardGameDescriptionNotFoundException.class)
    public ResponseEntity<Object> handleBoardGameDescriptionNotFoundException(BoardGameDescriptionNotFoundException e) {
        ExceptionBody exception = new ExceptionBody(
                e.getMessage(),
                HttpStatus.NOT_FOUND,
                ZonedDateTime.now()
        );

        return new ResponseEntity<>(exception, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BoardGameDescriptionAlreadyExistsException.class)
    public ResponseEntity<Object> handleBoardGameDescriptionAlreadyExistsException(BoardGameDescriptionAlreadyExistsException e) {
        ExceptionBody exception = new ExceptionBody(
                e.getMessage(),
                HttpStatus.CONFLICT,
                ZonedDateTime.now()
        );

        return new ResponseEntity<>(exception, HttpStatus.CONFLICT);
    }
}
