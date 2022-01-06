package com.wj.bgtavern.services;


import com.wj.bgtavern.exceptions.boardgameteammember.BoardGameTeamMemberAlreadyExistsException;
import com.wj.bgtavern.exceptions.boardgame.BoardGameNotFoundException;
import com.wj.bgtavern.exceptions.boardgameteammember.BoardGameTeamMemberNotFoundException;
import com.wj.bgtavern.exceptions.communitymember.CommunityMemberNotFoundException;
import com.wj.bgtavern.exceptions.communitymemberrole.CommunityMemberRoleNotFoundException;
import com.wj.bgtavern.models.BoardGameTeamMember;
import com.wj.bgtavern.models.CommunityMember;
import com.wj.bgtavern.models.CommunityMemberRole;
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


    public List<BoardGameTeamMember> getBoardGameTeamMembers(Long boardGameId) {
        if (!boardGameRepository.existsById(boardGameId)) {
            throw new BoardGameNotFoundException(boardGameId);
        }
        List<BoardGameTeamMember> boardGameTeamMembers = boardGameTeamMemberRepository.findAllByBoardGameId(boardGameId);
        for (BoardGameTeamMember boardGameTeamMember : boardGameTeamMembers) {
            // TODO: Check if member and role is selected with team members and if not, use a BoardGameTeamMemberService
            // TODO: to get boardGameTeamMembers with members and their roles
            if (!communityMemberRepository.existsById(boardGameTeamMember.getMember().getId()))
                throw new CommunityMemberNotFoundException(boardGameTeamMember.getMember().getId());
            if (!communityMemberRoleRepository.existsById(boardGameTeamMember.getMember().getId()))
                throw new CommunityMemberRoleNotFoundException(boardGameTeamMember.getMember().getId());

            CommunityMember communityMember = communityMemberRepository.getById(boardGameTeamMember.getMember().getId());
            CommunityMemberRole communityMemberRole = communityMemberRoleRepository.getById(boardGameTeamMember.getRole().getId());

            boardGameTeamMember.setMember(communityMember);
            boardGameTeamMember.setRole(communityMemberRole);
        }

        return boardGameTeamMembers;
    }

    public BoardGameTeamMember addBoardGameTeamMember(BoardGameTeamMember boardGameTeamMember) {
        if (!boardGameRepository.existsById(boardGameTeamMember.getBoardGameId()))
            throw new BoardGameNotFoundException(boardGameTeamMember.getBoardGameId());
        // TODO: Check if member and role is selected with team members and if not, use a BoardGameTeamMemberService
        // TODO: to get boardGameTeamMembers with members and their roles
        if (!communityMemberRepository.existsById(boardGameTeamMember.getMember().getId()))
            throw new CommunityMemberNotFoundException(boardGameTeamMember.getMember().getId());
        if (!communityMemberRoleRepository.existsById(boardGameTeamMember.getMember().getId()))
            throw new CommunityMemberRoleNotFoundException(boardGameTeamMember.getMember().getId());
        if (boardGameTeamMemberRepository.existsByBoardGameId(boardGameTeamMember.getBoardGameId()) &&
                boardGameTeamMemberRepository.existsByMemberId(boardGameTeamMember.getMember().getId()) &&
                boardGameTeamMemberRepository.existsByRoleId(boardGameTeamMember.getRole().getId()))
            throw new BoardGameTeamMemberAlreadyExistsException(
                    boardGameTeamMember.getBoardGameId(),
                    boardGameTeamMember.getMember().getId(),
                    boardGameTeamMember.getRole().getId());

        return boardGameTeamMemberRepository.save(boardGameTeamMember);
    }

    public BoardGameTeamMember editBoardGameTeamMember(BoardGameTeamMember boardGameTeamMember) {
        if (!boardGameTeamMemberRepository.existsById(boardGameTeamMember.getId()))
            throw new BoardGameTeamMemberNotFoundException(boardGameTeamMember.getId());
        // TODO: Check if member and role is selected with team members and if not, use a BoardGameTeamMemberService
        // TODO: to get boardGameTeamMembers with members and their roles
        if (boardGameTeamMemberRepository.existsByBoardGameId(boardGameTeamMember.getBoardGameId()) &&
            boardGameTeamMemberRepository.existsByMemberId(boardGameTeamMember.getMember().getId()) &&
            boardGameTeamMemberRepository.existsByRoleId(boardGameTeamMember.getRole().getId()))
            throw new BoardGameTeamMemberAlreadyExistsException(
                    boardGameTeamMember.getBoardGameId(),
                    boardGameTeamMember.getMember().getId(),
                    boardGameTeamMember.getRole().getId());

        return boardGameTeamMemberRepository.save(boardGameTeamMember);
    }

    public void deleteBoardGameTeamMember(Long id) {
        if (!boardGameTeamMemberRepository.existsById(id)) {
            throw new BoardGameTeamMemberNotFoundException(id);
        }
        boardGameTeamMemberRepository.deleteById(id);
    }
}
