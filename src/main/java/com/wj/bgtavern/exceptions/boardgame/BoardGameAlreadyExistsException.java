package com.wj.bgtavern.exceptions.boardgame;

public class BoardGameAlreadyExistsException extends RuntimeException{

    public BoardGameAlreadyExistsException(String name) {
        super("Board game with name " + name + " already exists in database");
    }
}
