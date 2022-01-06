package com.wj.bgtavern.controllers;

import com.wj.bgtavern.models.BoardGame;
import com.wj.bgtavern.models.BoardGameDescription;
import com.wj.bgtavern.models.dtos.BoardGameDto;
import com.wj.bgtavern.models.dtos.BoardGameHeaderDto;
import com.wj.bgtavern.models.dtos.mappers.BoardGameDescriptionMapper;
import com.wj.bgtavern.models.dtos.mappers.BoardGameDtoMapper;
import com.wj.bgtavern.models.dtos.mappers.BoardGameHeaderDtoMapper;
import com.wj.bgtavern.models.dtos.mappers.BoardGameMapper;
import com.wj.bgtavern.services.BoardGameDescriptionService;
import com.wj.bgtavern.services.BoardGameService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class BoardGameController {

    /* TODO: Dowiedzieć się czy zwracać DTO już z serwisu, czy mapować dopiero w kontrolerze,
     * TODO: dodatkowo czy można mieszać te dwa podejścia.
     */

    private final BoardGameService boardGameService;

    private final int PAGE_SIZE = 25;



    @GetMapping("/board_games")
    public List<BoardGameHeaderDto> getBoardGameHeaders(@RequestParam(required = false) Integer page,
                                                        @RequestParam(required = false) Integer size) {
        int pageNumber = (page != null && page > 0 ? page : 1)  - 1;
        int pageSize = size != null && size >= 0 ? size : PAGE_SIZE;
        return boardGameService.getBoardGameHeaders(pageNumber, pageSize);
    }

//    @GetMapping("/board_games")
//    public List<BoardGameHeaderDto> getBoardGameHeaders(@RequestParam(required = false) Integer page,
//                                                        @RequestParam(required = false) Integer size) {
//        int pageNumber = (page != null && page > 0 ? page : 1)  - 1;
//        int pageSize = size != null && size >= 0 ? size : PAGE_SIZE;
//        return BoardGameHeaderDtoMapper.mapToBoardGameHeaderDtos(
//                boardGameService.getBoardGameHeaders(pageNumber, pageSize));
//    }

    @GetMapping("/board_games/{id}")
    public BoardGameDto getSingleBoardGame(@PathVariable Long id) {
        return boardGameService.getSingleBoardGame(id);
    }

//    @GetMapping("/board_games/{id}")
//    public BoardGameDto getSingleBoardGame(@PathVariable Long id) {
//        BoardGameDescription description = boardGameDescriptionService.getBoardGameDescription(id);
//        BoardGame boardGame = boardGameService.getSingleBoardGame(id);
//        return BoardGameDtoMapper.mapToBoardGameDto(boardGame, description);
//    }

    @PostMapping("/board_games")
    public BoardGameDto addBoardGame(@RequestBody @Valid BoardGameDto boardGameDto) {
        return boardGameService.addBoardGame(boardGameDto);
    }

//    @PostMapping("/board_games")
//    public BoardGameDto addBoardGame(@RequestBody @Valid BoardGameDto boardGameDto) {
//        BoardGame boardGame = BoardGameMapper.mapToBoardGame(boardGameDto);
//        boardGame = boardGameService.addBoardGame(boardGame);
//        BoardGameDescription description = BoardGameDescriptionMapper.mapToBoardGameDescription(boardGame, boardGameDto);
//        try {
//            description = boardGameDescriptionService.addBoardGameDescription(description);
//        } finally {
//            // If there is sth wrong with inserting board game description
//            boardGameService.deleteBoardGame(boardGame.getId());
//        }
//        return BoardGameDtoMapper.mapToBoardGameDto(boardGame, description);
//    }

    @PutMapping("/board_games/{id}")
    public BoardGameDto editBoardGame(@PathVariable Long id, @RequestBody @Valid BoardGameDto boardGameDto) {
        return boardGameService.editBoardGame(id, boardGameDto);
    }

//    @PutMapping("/board_games/{id}")
//    public BoardGameDto editBoardGame(@PathVariable Long id, @RequestBody @Valid BoardGameDto boardGameDto) {
//        BoardGame boardGame = BoardGameMapper.mapToBoardGame(id, boardGameDto);
//        BoardGameDescription description = BoardGameDescriptionMapper.mapToBoardGameDescription(id, boardGameDto);
//        boardGame = boardGameService.editBoardGame(boardGame, description);
//        return BoardGameDtoMapper.mapToBoardGameDto(boardGame, description);
//    }

    @DeleteMapping("/board_games/{id}")
    public void deleteBoardGame(@PathVariable Long id) {
        boardGameService.deleteBoardGame(id);
    }

}
