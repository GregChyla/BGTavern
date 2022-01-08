package com.wj.bgtavern.controllers;

import com.wj.bgtavern.models.dtos.BoardGameTeamMemberRequestDto;
import com.wj.bgtavern.models.dtos.BoardGameTeamMemberResponseDto;
import com.wj.bgtavern.services.BoardGameTeamMemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BoardGameTeamMemberController {

    private final BoardGameTeamMemberService boardGameTeamMemberService;


    @GetMapping("/board_games/{boardGameId}/team_members")
    public List<BoardGameTeamMemberResponseDto> getBoardGameTeamMembers(@PathVariable Long boardGameId) {
        return boardGameTeamMemberService.getBoardGameTeamMembers(boardGameId);
    }

    @PostMapping("/board_games/{boardGameId}/team_members")
    public BoardGameTeamMemberResponseDto addBoardGameTeamMember(
            @PathVariable Long boardGameId,
            @RequestBody BoardGameTeamMemberRequestDto boardGameTeamMemberRequestDto) {
        return boardGameTeamMemberService.addBoardGameTeamMember(boardGameTeamMemberRequestDto, boardGameId);
    }

    @PutMapping("/board_games/{boardGameId}/team_members/{teamMemberId}")
    public BoardGameTeamMemberResponseDto editBoardGameTeamMember(
            @PathVariable Long boardGameId, @PathVariable Long teamMemberId,
            @RequestBody BoardGameTeamMemberRequestDto boardGameTeamMemberRequestDto) {
        return boardGameTeamMemberService.editBoardGameTeamMember(boardGameTeamMemberRequestDto, boardGameId, teamMemberId);
    }

    @DeleteMapping("/team_members/{id}")
    public void deleteBoardGameTeamMember(@PathVariable Long id) {
        boardGameTeamMemberService.deleteBoardGameTeamMember(id);
    }
}
