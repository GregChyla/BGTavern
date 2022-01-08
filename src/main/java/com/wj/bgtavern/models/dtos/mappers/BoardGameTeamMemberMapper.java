package com.wj.bgtavern.models.dtos.mappers;

import com.wj.bgtavern.models.*;
import com.wj.bgtavern.models.dtos.BoardGameTeamMemberRequestDto;
import com.wj.bgtavern.models.dtos.BoardGameTeamMemberResponseDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BoardGameTeamMemberMapper {


    public static List<BoardGameTeamMemberResponseDto> mapToBoardGameTeamMemberResponseDtos(List<BoardGameTeamMember> boardGameTeamMembers) {
        return boardGameTeamMembers.stream()
                .map(boardGameTeamMember -> mapToBoardGameTeamMemberResponseDto(boardGameTeamMember))
                .collect(Collectors.toList());
    }

    public static BoardGameTeamMemberResponseDto mapToBoardGameTeamMemberResponseDto(BoardGameTeamMember boardGameTeamMember) {
        CommunityMember communityMember = boardGameTeamMember.getMember();
        CommunityMemberRole communityMemberRole = boardGameTeamMember.getRole();

        return BoardGameTeamMemberResponseDto.builder()
                .id(boardGameTeamMember.getId())
                .memberName(communityMember.getName())
                .memberNationality(communityMember.getNationality())
                .roleName(communityMemberRole.getName())
                .build();
    }

    public static BoardGameTeamMember mapToBoardGameTeamMember(
            Long boardGameTeamMemberId,
            Long boardGameId,
            CommunityMember communityMember,
            CommunityMemberRole communityMemberRole) {
        BoardGameTeamMember boardGameTeamMember =
                mapToBoardGameTeamMember(boardGameId, communityMember, communityMemberRole);
        boardGameTeamMember.setId(boardGameTeamMemberId);
        return boardGameTeamMember;
    }

    public static BoardGameTeamMember mapToBoardGameTeamMember(
            Long boardGameId,
            CommunityMember communityMember,
            CommunityMemberRole communityMemberRole
    ) {
        BoardGameTeamMember boardGameTeamMember = new BoardGameTeamMember();
        boardGameTeamMember.setBoardGameId(boardGameId);
        boardGameTeamMember.setMember(communityMember);
        boardGameTeamMember.setRole(communityMemberRole);
        return boardGameTeamMember;
    }
}
