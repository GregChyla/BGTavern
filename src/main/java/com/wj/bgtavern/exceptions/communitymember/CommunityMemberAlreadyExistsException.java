package com.wj.bgtavern.exceptions.communitymember;

public class CommunityMemberAlreadyExistsException extends RuntimeException{

    public CommunityMemberAlreadyExistsException(String name, String nationality) {
        super("Community member with name " + name + " and nationality " + nationality + " already exists in database");
    }
}
