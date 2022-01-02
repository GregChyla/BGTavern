package com.wj.bgtavern.controller;

import com.wj.bgtavern.dto.BoardGameDto;
import com.wj.bgtavern.dto.mapper.BoardGameDtoMapper;
import com.wj.bgtavern.model.BoardGame;
import com.wj.bgtavern.service.BoardGameService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BoardGameController {

    private final BoardGameService boardGameService;

    private final int PAGE_SIZE = 25;


    @GetMapping("/board_games")
    public List<BoardGameDto> getBoardGames(@RequestParam(required = false) Integer page,
                                            @RequestParam(required = false) Integer size) {
        int pageNumber = (page != null && page > 0 ? page : 1)  - 1;
        int pageSize = size != null && size >= 0 ? size : PAGE_SIZE;
        return BoardGameDtoMapper.mapToBoardGameDtos(
                boardGameService.getBoardGames(pageNumber, pageSize));
    }

    @GetMapping("/board_games/{id}")
    public BoardGame getSingleBoardGame(@PathVariable Long id) {
        return boardGameService.getSingleBoardGame(id);
    }

}
