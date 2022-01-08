package com.wj.bgtavern.models.dtos.mappers;

import com.wj.bgtavern.models.CommunityMember;
import com.wj.bgtavern.models.dtos.CommunityMemberRequestDto;
import com.wj.bgtavern.models.dtos.CommunityMemberResponseDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CommunityMemberMapper {

    public static CommunityMember mapToCommunityMember(
            CommunityMemberRequestDto communityMemberRequestDto) {
        CommunityMember communityMember = new CommunityMember();
        communityMember.setName(communityMemberRequestDto.getName());
        communityMember.setNationality(communityMemberRequestDto.getNationality());
        return communityMember;
    }

    public static CommunityMember mapToCommunityMember(
            CommunityMemberRequestDto communityMemberRequestDto, Long id) {
        CommunityMember communityMember = mapToCommunityMember(communityMemberRequestDto);
        communityMember.setId(id);
        return communityMember;
    }

    public static List<CommunityMemberResponseDto> mapToCommunityMemberResponseDtos(List<CommunityMember> communityMembers) {
        return communityMembers.stream()
                .map(communityMember -> mapToCommunityMemberResponseDto(communityMember))
                .collect(Collectors.toList());
    }

    public static CommunityMemberResponseDto mapToCommunityMemberResponseDto(CommunityMember communityMember) {
        return CommunityMemberResponseDto.builder()
                .id(communityMember.getId())
                .name(communityMember.getName())
                .nationality(communityMember.getNationality())
                .build();
    }
}
