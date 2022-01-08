package com.wj.bgtavern.models.dtos.mappers;

import com.wj.bgtavern.models.CommunityMember;
import com.wj.bgtavern.models.CommunityMemberRole;
import com.wj.bgtavern.models.dtos.CommunityMemberRequestDto;
import com.wj.bgtavern.models.dtos.CommunityMemberResponseDto;
import com.wj.bgtavern.models.dtos.CommunityMemberRoleRequestDto;
import com.wj.bgtavern.models.dtos.CommunityMemberRoleResponseDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CommunityMemberRoleMapper {

    public static CommunityMemberRole mapToCommunityMemberRole(
            CommunityMemberRoleRequestDto communityMemberRoleRequestDto, Long id) {
        CommunityMemberRole communityMemberRole =mapToCommunityMemberRole(communityMemberRoleRequestDto);
        communityMemberRole.setId(id);
        return communityMemberRole;
    }

    public static CommunityMemberRole mapToCommunityMemberRole(CommunityMemberRoleRequestDto communityMemberRoleRequestDto) {
        CommunityMemberRole communityMemberRole = new CommunityMemberRole();
        communityMemberRole.setName(communityMemberRoleRequestDto.getName());
        communityMemberRole.setDescription(communityMemberRoleRequestDto.getDescription());
        return communityMemberRole;
    }

    public static List<CommunityMemberRoleResponseDto> mapToCommunityMemberRoleResponseDtos(List<CommunityMemberRole> communityMemberRoles) {
        return communityMemberRoles.stream()
                .map(communityMemberRole -> mapToCommunityMemberRoleResponseDto(communityMemberRole))
                .collect(Collectors.toList());
    }

    public static CommunityMemberRoleResponseDto mapToCommunityMemberRoleResponseDto(CommunityMemberRole communityMemberRole) {
        return CommunityMemberRoleResponseDto.builder()
                .id(communityMemberRole.getId())
                .name(communityMemberRole.getName())
                .description(communityMemberRole.getDescription())
                .build();
    }
}
