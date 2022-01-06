package com.wj.bgtavern.controllers;

import com.wj.bgtavern.models.BoardGameTeamMember;
import com.wj.bgtavern.models.dtos.BoardGameTeamMemberDto;
import com.wj.bgtavern.models.dtos.mappers.BoardGameTeamMemberDtoMapper;
import com.wj.bgtavern.services.BoardGameTeamMemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BoardGameTeamMemberController {

    private final BoardGameTeamMemberService boardGameTeamMemberService;


    @GetMapping("/board_games/{id}/team_members")
    public List<BoardGameTeamMemberDto> getBoardGameTeamMembers(@PathVariable(name="id") Long boardGameId) {
        List<BoardGameTeamMember> boardGameTeamMembers = boardGameTeamMemberService.getBoardGameTeamMembers(boardGameId);

        return BoardGameTeamMemberDtoMapper.mapToBoardGameDtos(boardGameTeamMembers);
    }

    @PostMapping("/board_games/{id}/team_members")
    public BoardGameTeamMember addBoardGameTeamMember(@PathVariable(name="id") Long boardGameId, @RequestBody BoardGameTeamMember boardGameTeamMember) {
        boardGameTeamMember.setBoardGameId(boardGameId);
        return boardGameTeamMemberService.addBoardGameTeamMember(boardGameTeamMember);
    }

    @PutMapping("/team_members/{id}")
    public BoardGameTeamMember editBoardGameTeamMember(@PathVariable Long id, @RequestBody BoardGameTeamMember boardGameTeamMember) {
        boardGameTeamMember.setId(id);
        return boardGameTeamMemberService.editBoardGameTeamMember(boardGameTeamMember);
    }

    @DeleteMapping("/team_members/{id}")
    public void deleteBoardGameTeamMember(@PathVariable Long id) {
        boardGameTeamMemberService.deleteBoardGameTeamMember(id);
    }
}
