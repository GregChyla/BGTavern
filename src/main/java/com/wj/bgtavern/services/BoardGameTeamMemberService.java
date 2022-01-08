package com.wj.bgtavern.services;


import com.wj.bgtavern.exceptions.boardgame.BoardGameNotFoundException;
import com.wj.bgtavern.exceptions.boardgameteammember.BoardGameTeamMemberAlreadyExistsException;
import com.wj.bgtavern.exceptions.boardgameteammember.BoardGameTeamMemberNotFoundException;
import com.wj.bgtavern.exceptions.communitymember.CommunityMemberNotFoundException;
import com.wj.bgtavern.exceptions.communitymemberrole.CommunityMemberRoleNotFoundException;
import com.wj.bgtavern.models.BoardGameTeamMember;
import com.wj.bgtavern.models.CommunityMember;
import com.wj.bgtavern.models.CommunityMemberRole;
import com.wj.bgtavern.models.dtos.BoardGameTeamMemberRequestDto;
import com.wj.bgtavern.models.dtos.BoardGameTeamMemberResponseDto;
import com.wj.bgtavern.models.dtos.mappers.BoardGameTeamMemberMapper;
import com.wj.bgtavern.repositories.BoardGameRepository;
import com.wj.bgtavern.repositories.BoardGameTeamMemberRepository;
import com.wj.bgtavern.repositories.CommunityMemberRepository;
import com.wj.bgtavern.repositories.CommunityMemberRoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardGameTeamMemberService {

    private final BoardGameTeamMemberRepository boardGameTeamMemberRepository;
    private final BoardGameRepository boardGameRepository;
    private final CommunityMemberRepository communityMemberRepository;
    private final CommunityMemberRoleRepository communityMemberRoleRepository;

    public List<BoardGameTeamMemberResponseDto> getBoardGameTeamMembers(Long boardGameId) {
        if (!boardGameRepository.existsById(boardGameId))
            throw new BoardGameNotFoundException(boardGameId);

        List<BoardGameTeamMember> boardGameTeamMembers = boardGameTeamMemberRepository.findAllByBoardGameId(boardGameId);
        return BoardGameTeamMemberMapper.mapToBoardGameTeamMemberResponseDtos(boardGameTeamMembers);
    }

    public BoardGameTeamMemberResponseDto addBoardGameTeamMember(
             BoardGameTeamMemberRequestDto boardGameTeamMemberRequestDto, Long boardGameId) {
        long communityMemberId = boardGameTeamMemberRequestDto.getCommunityMemberId();
        long communityMemberRoleId = boardGameTeamMemberRequestDto.getCommunityMemberRoleId();

        if (!boardGameRepository.existsById(boardGameId))
            throw new BoardGameNotFoundException(boardGameId);
        if (boardGameTeamMemberRepository.existsByAllForeignIds(boardGameId, communityMemberId, communityMemberRoleId))
            throw new BoardGameTeamMemberAlreadyExistsException(boardGameId, communityMemberId, communityMemberRoleId);

        CommunityMember communityMember = communityMemberRepository.findById(communityMemberId).orElseThrow(
                () -> new CommunityMemberNotFoundException(communityMemberId));
        CommunityMemberRole communityMemberRole = communityMemberRoleRepository.findById(communityMemberRoleId).orElseThrow(
                () -> new CommunityMemberRoleNotFoundException(communityMemberRoleId));

        BoardGameTeamMember boardGameTeamMember =
                BoardGameTeamMemberMapper.mapToBoardGameTeamMember(boardGameId, communityMember, communityMemberRole);
        boardGameTeamMemberRepository.save(boardGameTeamMember);
        return BoardGameTeamMemberMapper.mapToBoardGameTeamMemberResponseDto(boardGameTeamMember);
    }

    public BoardGameTeamMemberResponseDto editBoardGameTeamMember(
                BoardGameTeamMemberRequestDto boardGameTeamMemberRequestDto,
                Long boardGameId, Long teamMemberId) {
        long communityMemberId = boardGameTeamMemberRequestDto.getCommunityMemberId();
        long communityMemberRoleId = boardGameTeamMemberRequestDto.getCommunityMemberRoleId();

        if (!boardGameTeamMemberRepository.existsById(teamMemberId))
            throw new BoardGameTeamMemberNotFoundException(teamMemberId);
        if (!boardGameRepository.existsById(boardGameId))
            throw new BoardGameNotFoundException(boardGameId);
        if (boardGameTeamMemberRepository.existsByAllForeignIdsAndNotWithId(boardGameId, communityMemberId, communityMemberRoleId, teamMemberId))
            throw new BoardGameTeamMemberAlreadyExistsException(boardGameId, communityMemberId, communityMemberRoleId);

        CommunityMember communityMember = communityMemberRepository.findById(communityMemberId).orElseThrow(
                () -> new CommunityMemberNotFoundException(communityMemberId));
        CommunityMemberRole communityMemberRole = communityMemberRoleRepository.findById(communityMemberRoleId).orElseThrow(
                () -> new CommunityMemberRoleNotFoundException(communityMemberRoleId));

        BoardGameTeamMember boardGameTeamMember =
                BoardGameTeamMemberMapper.mapToBoardGameTeamMember(
                        teamMemberId, boardGameId, communityMember, communityMemberRole);
        boardGameTeamMemberRepository.save(boardGameTeamMember);
        return BoardGameTeamMemberMapper.mapToBoardGameTeamMemberResponseDto(boardGameTeamMember);
    }

    public void deleteBoardGameTeamMember(Long id) {
        if (!boardGameTeamMemberRepository.existsById(id))
            throw new BoardGameTeamMemberNotFoundException(id);

        boardGameTeamMemberRepository.deleteById(id);
    }

}
