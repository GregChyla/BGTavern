package com.wj.bgtavern.controllers;

import com.wj.bgtavern.models.CommunityMemberRole;
import com.wj.bgtavern.models.dtos.CommunityMemberRoleRequestDto;
import com.wj.bgtavern.models.dtos.CommunityMemberRoleResponseDto;
import com.wj.bgtavern.services.CommunityMemberRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class CommunityMemberRoleController {

    private final CommunityMemberRoleService communityMemberRoleService;


    @GetMapping("/community_member_roles")
    public List<CommunityMemberRoleResponseDto> getCommunityMemberRoles() {
        return communityMemberRoleService.getCommunityMemberRoles();
    }

    @PostMapping("/community_member_roles")
    public CommunityMemberRoleResponseDto addCommunityMemberRole(@RequestBody @Valid CommunityMemberRoleRequestDto communityMemberRoleRequestDto) {
        return communityMemberRoleService.addCommunityMemberRole(communityMemberRoleRequestDto);
    }

    @PutMapping("/community_member_roles/{id}")
    public CommunityMemberRoleResponseDto editCommunityMemberRole(
            @PathVariable Long id, @RequestBody CommunityMemberRoleRequestDto communityMemberRoleRequestDto) {
        return communityMemberRoleService.editCommunityMemberRole(id, communityMemberRoleRequestDto);
    }

    @DeleteMapping("/community_member_roles/{id}")
    public void deleteCommunityMemberRole(@PathVariable Long id) {
        communityMemberRoleService.deleteCommunityMemberRole(id);
    }
}
