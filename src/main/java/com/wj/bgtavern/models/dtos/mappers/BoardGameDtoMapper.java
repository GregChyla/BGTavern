package com.wj.bgtavern.models.dtos.mappers;

import com.wj.bgtavern.models.BoardGameDescription;
import com.wj.bgtavern.models.dtos.BoardGameDto;
import com.wj.bgtavern.models.BoardGame;
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
//        BoardGameDescription boardGameDescription = boardGame.getDescription();
        return BoardGameDto.builder()
                .id(boardGame.getId())
                .name(boardGame.getName())
                .playingTime(boardGame.getPlayingTime())
                .age(boardGame.getAge())
                .minPlayersNumber(boardGame.getMinPlayersNumber())
                .maxPlayersNumber(boardGame.getMaxPlayersNumber())
                .complexity(boardGame.getComplexity())
                .languageDependence(boardGame.getLanguageDependence())
//                .description(boardGameDescription != null ? boardGameDescription.getDescription() : "")
                .build();
    }

    public static BoardGameDto mapToBoardGameDto(BoardGame boardGame, BoardGameDescription boardGameDescription) {
        return BoardGameDto.builder()
                .id(boardGame.getId())
                .name(boardGame.getName())
                .playingTime(boardGame.getPlayingTime())
                .age(boardGame.getAge())
                .minPlayersNumber(boardGame.getMinPlayersNumber())
                .maxPlayersNumber(boardGame.getMaxPlayersNumber())
                .complexity(boardGame.getComplexity())
                .languageDependence(boardGame.getLanguageDependence())
                .description(boardGameDescription != null ? boardGameDescription.getDescription() : "")
                .build();
    }

}
