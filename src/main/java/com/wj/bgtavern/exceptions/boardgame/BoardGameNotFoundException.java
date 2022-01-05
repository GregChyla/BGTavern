package com.wj.bgtavern.exceptions.boardgame;

public class BoardGameNotFoundException extends RuntimeException {

    public BoardGameNotFoundException(Long id) {
        super("No such board game with id: " + id);
    }
}
