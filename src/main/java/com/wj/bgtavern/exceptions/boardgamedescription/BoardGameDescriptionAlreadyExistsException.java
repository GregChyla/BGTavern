package com.wj.bgtavern.exceptions.boardgamedescription;

public class BoardGameDescriptionAlreadyExistsException extends RuntimeException{

    public BoardGameDescriptionAlreadyExistsException(Long id) {
        super("Description to board game with id " + id + " already exists in database");
    }
}
