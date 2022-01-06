package com.wj.bgtavern.models.dtos.mappers;

import com.wj.bgtavern.models.BoardGame;
import com.wj.bgtavern.models.BoardGameDescription;
import com.wj.bgtavern.models.dtos.BoardGameDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BoardGameDescriptionMapper {


    public static BoardGameDescription mapToBoardGameDescription(BoardGameDto boardGameDto) {
        BoardGameDescription description = new BoardGameDescription();
        description.setDescription(boardGameDto.getDescription() != null ? boardGameDto.getDescription() : ""); // there always should be a description even if empty
        return description;
    }

    public static BoardGameDescription mapToBoardGameDescription(Long id, BoardGameDto boardGameDto) {
        BoardGameDescription description = new BoardGameDescription();
        description.setBoardGameId(id);
        description.setDescription(boardGameDto.getDescription() != null ? boardGameDto.getDescription() : ""); // there always should be a description even if empty
        return description;
    }

    public static BoardGameDescription mapToBoardGameDescription(BoardGame boardGame, BoardGameDto boardGameDto) {
        BoardGameDescription description = new BoardGameDescription();
        description.setBoardGame(boardGame);
        description.setDescription(boardGameDto.getDescription() != null ? boardGameDto.getDescription() : ""); // there always should be a description even if empty
        return description;
    }
}
