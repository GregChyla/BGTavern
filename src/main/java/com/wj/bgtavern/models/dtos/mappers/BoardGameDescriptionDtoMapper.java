package com.wj.bgtavern.models.dtos.mappers;

import com.wj.bgtavern.models.BoardGame;
import com.wj.bgtavern.models.BoardGameDescription;
import com.wj.bgtavern.models.dtos.BoardGameDescriptionDto;
import com.wj.bgtavern.models.dtos.BoardGameDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BoardGameDescriptionDtoMapper {

    public static BoardGameDescriptionDto mapToBoardGameDescriptionDto(BoardGameDescription boardGameDescription) {
        return BoardGameDescriptionDto.builder()
                .boardGameId(boardGameDescription.getBoardGameId())
                .description(boardGameDescription.getDescription())
                .build();
    }
}
