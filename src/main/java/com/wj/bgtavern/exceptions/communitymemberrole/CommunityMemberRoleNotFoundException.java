package com.wj.bgtavern.exceptions.communitymemberrole;

public class CommunityMemberRoleNotFoundException extends RuntimeException {

    public CommunityMemberRoleNotFoundException(Long id) {
        super("No such community member role with id: " + id);
    }
}
