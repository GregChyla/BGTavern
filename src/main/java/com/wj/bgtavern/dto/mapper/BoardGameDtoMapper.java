package com.wj.bgtavern.dto.mapper;

import com.wj.bgtavern.dto.BoardGameDto;
import com.wj.bgtavern.model.BoardGame;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BoardGameDtoMapper {

    public static List<BoardGameDto> mapToBoardGameDtos(List<BoardGame> boardGames) {
        return boardGames.stream()
                .map(boardGame -> mapToBoardGameDto(boardGame))
                .collect(Collectors.toList());
    }

    private static BoardGameDto mapToBoardGameDto(BoardGame boardGame) {
        return BoardGameDto.builder()
                .id(boardGame.getId())
                .name(boardGame.getName())
                .playingTime(boardGame.getPlayingTime())
                .age(boardGame.getAge())
                .minPlayersNumber(boardGame.getMinPlayersNumber())
                .maxPlayersNumber(boardGame.getMaxPlayersNumber())
                .complexity(boardGame.getComplexity())
                .languageDependence(boardGame.getLanguageDependence())
                .build();
    }

}
