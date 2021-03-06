package com.wj.bgtavern.controllers;

import com.wj.bgtavern.models.dtos.BoardGameRequestDto;
import com.wj.bgtavern.models.dtos.BoardGameResponseDto;
import com.wj.bgtavern.models.dtos.BoardGameHeaderDto;
import com.wj.bgtavern.services.BoardGameService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class BoardGameController {

    private final BoardGameService boardGameService;

    private final int PAGE_SIZE = 25;



    @GetMapping("/board_games")
    public List<BoardGameHeaderDto> getBoardGameHeaders(@RequestParam(required = false) Integer page,
                                                        @RequestParam(required = false) Integer size) {
        int pageNumber = (page != null && page > 0 ? page : 1)  - 1;
        int pageSize = size != null && size >= 0 ? size : PAGE_SIZE;
        return boardGameService.getBoardGameHeaders(pageNumber, pageSize);
    }

    @GetMapping("/board_games/{id}")
    public BoardGameResponseDto getSingleBoardGame(@PathVariable Long id) {
        return boardGameService.getSingleBoardGame(id);
    }

    @PostMapping("/board_games")
    public BoardGameResponseDto addBoardGame(@RequestBody @Valid BoardGameRequestDto boardGameRequestDto) {
        return boardGameService.addBoardGame(boardGameRequestDto);
    }

    @PutMapping("/board_games/{id}")
    public BoardGameResponseDto editBoardGame(@PathVariable Long id, @RequestBody @Valid BoardGameRequestDto boardGameRequestDto) {
        return boardGameService.editBoardGame(id, boardGameRequestDto);
    }

    @DeleteMapping("/board_games/{id}")
    public void deleteBoardGame(@PathVariable Long id) {
        boardGameService.deleteBoardGame(id);
    }


}

