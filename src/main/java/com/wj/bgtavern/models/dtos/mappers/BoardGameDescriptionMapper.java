package com.wj.bgtavern.models.dtos.mappers;

import com.wj.bgtavern.models.BoardGame;
import com.wj.bgtavern.models.BoardGameDescription;
import com.wj.bgtavern.models.dtos.BoardGameDescriptionDto;
import com.wj.bgtavern.models.dtos.BoardGameDescriptionRequestDto;
import com.wj.bgtavern.models.dtos.BoardGameRequestDto;
import com.wj.bgtavern.models.dtos.BoardGameResponseDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BoardGameDescriptionMapper {

    public static BoardGameDescriptionDto mapToBoardGameDescriptionDto(BoardGameDescription boardGameDescription) {
        return BoardGameDescriptionDto.builder()
                .boardGameId(boardGameDescription.getBoardGameId())
                .description(boardGameDescription.getDescription())
                .build();
    }

    public static BoardGameDescription mapToBoardGameDescription(Long boardGameId, BoardGameDescriptionRequestDto boardGameDescriptionRequestDto) {
        BoardGameDescription description = new BoardGameDescription();
        description.setDescription(boardGameDescriptionRequestDto.getDescription() != null ? boardGameDescriptionRequestDto.getDescription() : "");
        description.setBoardGameId(boardGameId);
        return description;
    }

    public static BoardGameDescription mapToBoardGameDescription(Long id, BoardGameRequestDto boardGameRequestDto) {
        BoardGameDescription description = mapToBoardGameDescription(boardGameRequestDto);
        description.setBoardGameId(id);
        return description;
    }

    public static BoardGameDescription mapToBoardGameDescription(BoardGameRequestDto boardGameRequestDto) {
        BoardGameDescription description = new BoardGameDescription();
        description.setDescription(boardGameRequestDto.getDescription() != null ? boardGameRequestDto.getDescription() : ""); // there always should be a description even if empty
        return description;
    }

    public static BoardGameDescription mapToBoardGameDescription(BoardGame boardGame, BoardGameRequestDto boardGameRequestDto) {
        BoardGameDescription description = new BoardGameDescription();
        description.setBoardGameId(boardGame.getId());
        description.setBoardGame(boardGame);
        description.setDescription(boardGameRequestDto.getDescription() != null ? boardGameRequestDto.getDescription() : ""); // there always should be a description even if empty
        return description;
    }
}
