package com.wj.bgtavern.models.dtos.mappers;

import com.wj.bgtavern.models.*;
import com.wj.bgtavern.models.dtos.BoardGameDto;
import com.wj.bgtavern.models.dtos.BoardGameTeamMemberDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BoardGameTeamMemberDtoMapper {


    public static List<BoardGameTeamMemberDto> mapToBoardGameDtos(List<BoardGameTeamMember> boardGameTeamMembers) {
        return boardGameTeamMembers.stream()
                .map(boardGameTeamMember -> mapToBoardGameTeamMemberDto(boardGameTeamMember))
                .collect(Collectors.toList());
    }

    private static BoardGameTeamMemberDto mapToBoardGameTeamMemberDto(BoardGameTeamMember boardGameTeamMember) {
        CommunityMember communityMember = boardGameTeamMember.getMember();
        CommunityMemberRole communityMemberRole = boardGameTeamMember.getRole();

        return BoardGameTeamMemberDto.builder()
                .id(boardGameTeamMember.getId())
                .memberName(communityMember.getName())
                .memberNationality(communityMember.getNationality())
                .roleName(communityMemberRole.getName())
                .build();
    }
}
