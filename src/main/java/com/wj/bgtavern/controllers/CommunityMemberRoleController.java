package com.wj.bgtavern.controllers;

import com.wj.bgtavern.models.CommunityMemberRole;
import com.wj.bgtavern.services.CommunityMemberRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CommunityMemberRoleController {

    private final CommunityMemberRoleService communityMemberRoleService;


    @GetMapping("/community_member_roles")
    public List<CommunityMemberRole> getCommunityMemberRoles() {
        return communityMemberRoleService.getCommunityMemberRoles();
    }

    @PostMapping("/community_member_roles")
    public CommunityMemberRole addCommunityMemberRole(@RequestBody CommunityMemberRole communityMemberRole) {
        return communityMemberRoleService.addCommunityMemberRole(communityMemberRole);
    }

    @PutMapping("/community_member_roles/{id}")
    public CommunityMemberRole editCommunityMemberRole(@PathVariable Long id, @RequestBody CommunityMemberRole communityMemberRole) {
        communityMemberRole.setId(id);
        return communityMemberRoleService.editCommunityMemberRole(communityMemberRole);
    }
}
