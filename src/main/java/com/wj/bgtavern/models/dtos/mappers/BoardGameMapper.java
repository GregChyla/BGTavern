package com.wj.bgtavern.models.dtos.mappers;

import com.wj.bgtavern.models.BoardGame;
import com.wj.bgtavern.models.dtos.BoardGameDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BoardGameMapper {

    public static BoardGame mapToBoardGame(BoardGameDto boardGameDto) {
        BoardGame boardGame = new BoardGame();
        boardGame.setName(boardGameDto.getName());
        boardGame.setPlayingTime(boardGameDto.getPlayingTime());
        boardGame.setAge(boardGameDto.getAge());
        boardGame.setMinPlayersNumber(boardGameDto.getMinPlayersNumber());
        boardGame.setMaxPlayersNumber(boardGameDto.getMaxPlayersNumber());
        boardGame.setComplexity(boardGameDto.getComplexity());
        boardGame.setLanguageDependence(boardGameDto.getLanguageDependence());
        return boardGame;
    }

    public static BoardGame mapToBoardGame(Long id, BoardGameDto boardGameDto) {
        BoardGame boardGame = new BoardGame();
        boardGame.setId(id);
        boardGame.setName(boardGameDto.getName());
        boardGame.setPlayingTime(boardGameDto.getPlayingTime());
        boardGame.setAge(boardGameDto.getAge());
        boardGame.setMinPlayersNumber(boardGameDto.getMinPlayersNumber());
        boardGame.setMaxPlayersNumber(boardGameDto.getMaxPlayersNumber());
        boardGame.setComplexity(boardGameDto.getComplexity());
        boardGame.setLanguageDependence(boardGameDto.getLanguageDependence());
        return boardGame;
    }
}
