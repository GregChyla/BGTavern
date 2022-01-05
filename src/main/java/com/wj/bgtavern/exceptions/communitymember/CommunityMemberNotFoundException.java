package com.wj.bgtavern.exceptions.communitymember;

public class CommunityMemberNotFoundException extends RuntimeException {

    public CommunityMemberNotFoundException(Long id) {
        super("No such community member with id: " + id);
    }
}
