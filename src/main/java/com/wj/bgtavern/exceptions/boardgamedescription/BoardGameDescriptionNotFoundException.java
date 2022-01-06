package com.wj.bgtavern.exceptions.boardgamedescription;

public class BoardGameDescriptionNotFoundException extends RuntimeException {

    public BoardGameDescriptionNotFoundException(Long id) {
        super("No such board game description with id: " + id);
    }
}
