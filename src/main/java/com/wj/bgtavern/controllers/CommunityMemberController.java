package com.wj.bgtavern.controllers;

import com.wj.bgtavern.models.CommunityMember;
import com.wj.bgtavern.services.CommunityMemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CommunityMemberController {

    private final CommunityMemberService communityMemberService;

    @GetMapping("/community_members")
    public List<CommunityMember> getCommunityMembers(){
        return communityMemberService.getCommunityMembers();
    }

    @PostMapping("/community_members")
    public CommunityMember addCommunityMember(@RequestBody CommunityMember communityMember) {
        return communityMemberService.addCommunityMember(communityMember);
    }

    @PutMapping("/community_members/{id}")
    public CommunityMember editCommunityMember(@PathVariable Long id, @RequestBody CommunityMember communityMember) {
        communityMember.setId(id);
        return communityMemberService.editCommunityMember(communityMember);
    }

    @DeleteMapping("/community_members/{id}")
    public void deleteCommunityMember(@PathVariable Long id) {
        communityMemberService.deleteCommunityMember(id);
    }
}
