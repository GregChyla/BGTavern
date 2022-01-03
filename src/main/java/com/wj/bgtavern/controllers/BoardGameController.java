package com.wj.bgtavern.controllers;

import com.wj.bgtavern.models.BoardGameDescription;
import com.wj.bgtavern.models.dtos.BoardGameDto;
import com.wj.bgtavern.models.dtos.BoardGameHeaderDto;
import com.wj.bgtavern.models.dtos.mappers.BoardGameDescriptionMapper;
import com.wj.bgtavern.models.dtos.mappers.BoardGameDtoMapper;
import com.wj.bgtavern.models.BoardGame;
import com.wj.bgtavern.models.dtos.mappers.BoardGameHeaderDtoMapper;
import com.wj.bgtavern.models.dtos.mappers.BoardGameMapper;
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
        return BoardGameHeaderDtoMapper.mapToBoardGameHeaderDtos(
                boardGameService.getBoardGames(pageNumber, pageSize));
    }

    @GetMapping("/board_games/{id}")
    public BoardGameDto getSingleBoardGame(@PathVariable Long id) {
        return BoardGameDtoMapper.mapToBoardGameDto(boardGameService.getSingleBoardGame(id));
    }

    @PostMapping("/board_games")
    public BoardGameDto addBoardGame(@RequestBody @Valid BoardGameDto boardGameDto) {
        BoardGame boardGame = BoardGameMapper.mapToBoardGame(boardGameDto);
        BoardGameDescription description = BoardGameDescriptionMapper.mapToBoardGameDescription(boardGameDto);
        return BoardGameDtoMapper.mapToBoardGameDto(boardGameService.addBoardGame(boardGame, description));
    }

    @PutMapping("/board_games/{id}")
    public BoardGameDto editBoardGame(@PathVariable Long id, @RequestBody @Valid BoardGameDto boardGameDto) {
        BoardGame boardGame = BoardGameMapper.mapToBoardGame(id, boardGameDto);
        BoardGameDescription description = BoardGameDescriptionMapper.mapToBoardGameDescription(id, boardGameDto);
        return BoardGameDtoMapper.mapToBoardGameDto(boardGameService.editBoardGame(boardGame, description));
    }

    @DeleteMapping("/board_games/{id}")
    public void deleteBoardGame(@PathVariable Long id) {
        boardGameService.deleteBoardGame(id);
    }

}
