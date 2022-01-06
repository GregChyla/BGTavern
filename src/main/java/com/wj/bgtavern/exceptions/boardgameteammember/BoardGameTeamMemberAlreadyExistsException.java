package com.wj.bgtavern.exceptions.boardgameteammember;

public class BoardGameTeamMemberAlreadyExistsException extends RuntimeException {
    public BoardGameTeamMemberAlreadyExistsException(long boardGameId, long communityMemberId, long communityMemberRoleId) {
        super("Community member with id " + communityMemberId
                + ", role id " + communityMemberRoleId
                + " and board game id " + boardGameId + " already exists.");
    }
}
