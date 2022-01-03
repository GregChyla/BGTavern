package com.wj.bgtavern.models.dtos.mappers;

import com.wj.bgtavern.models.BoardGame;
import com.wj.bgtavern.models.dtos.BoardGameHeaderDto;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;


@NoArgsConstructor
public class BoardGameHeaderDtoMapper {

    public static List<BoardGameHeaderDto> mapToBoardGameHeaderDtos(List<BoardGame> boardGames) {
        return boardGames.stream()
                .map(boardGame -> mapToBoardGameHeaderDto(boardGame))
                .collect(Collectors.toList());
    }

    private static BoardGameHeaderDto mapToBoardGameHeaderDto(BoardGame boardGame) {
        return BoardGameHeaderDto.builder()
                .id(boardGame.getId())
                .name(boardGame.getName())
                .build();
    }
}
