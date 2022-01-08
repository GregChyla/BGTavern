package com.wj.bgtavern.controllers;

import com.wj.bgtavern.models.CommunityMember;
import com.wj.bgtavern.models.dtos.CommunityMemberRequestDto;
import com.wj.bgtavern.models.dtos.CommunityMemberResponseDto;
import com.wj.bgtavern.services.CommunityMemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class CommunityMemberController {

    private final CommunityMemberService communityMemberService;

    @GetMapping("/community_members")
    public List<CommunityMemberResponseDto> getCommunityMembers(){
        return communityMemberService.getCommunityMembers();
    }

    @PostMapping("/community_members")
    public CommunityMemberResponseDto addCommunityMember(@RequestBody @Valid CommunityMemberRequestDto communityMemberRequestDto) {
        return communityMemberService.addCommunityMember(communityMemberRequestDto);
    }

    @PutMapping("/community_members/{id}")
    public CommunityMemberResponseDto editCommunityMember(
            @PathVariable Long id, @RequestBody @Valid CommunityMemberRequestDto communityMemberRequestDto) {
        return communityMemberService.editCommunityMember(id, communityMemberRequestDto);
    }

    @DeleteMapping("/community_members/{id}")
    public void deleteCommunityMember(@PathVariable Long id) {
        communityMemberService.deleteCommunityMember(id);
    }
}
