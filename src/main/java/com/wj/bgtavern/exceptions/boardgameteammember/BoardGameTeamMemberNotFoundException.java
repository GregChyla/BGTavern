package com.wj.bgtavern.exceptions.boardgameteammember;

public class BoardGameTeamMemberNotFoundException extends RuntimeException {

    public BoardGameTeamMemberNotFoundException(Long id) {
        super("No such board game team member with " + id);
    }
}
