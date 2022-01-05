package com.wj.bgtavern.exceptions.communitymemberrole;

public class CommunityMemberRoleAlreadyExistsException extends RuntimeException{

    public CommunityMemberRoleAlreadyExistsException(String name) {
        super("Community member role " + name + " already exists in database");
    }
}
