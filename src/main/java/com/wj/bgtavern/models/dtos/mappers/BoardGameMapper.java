package com.wj.bgtavern.models.dtos.mappers;

import com.wj.bgtavern.models.BoardGame;
import com.wj.bgtavern.models.BoardGameDescription;
import com.wj.bgtavern.models.dtos.BoardGameRequestDto;
import com.wj.bgtavern.models.dtos.BoardGameResponseDto;
import com.wj.bgtavern.models.dtos.BoardGameHeaderDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BoardGameMapper {

    public static BoardGame mapToBoardGame(Long id, BoardGameRequestDto boardGameRequestDto) {
        BoardGame boardGame = mapToBoardGame(boardGameRequestDto);
        boardGame.setId(id);
        boardGame.getDescription().setBoardGameId(id);
        return boardGame;
    }

    public static BoardGame mapToBoardGame(BoardGameRequestDto boardGameRequestDto) {
        BoardGame boardGame = new BoardGame();
        boardGame.setName(boardGameRequestDto.getName());
        boardGame.setPlayingTime(boardGameRequestDto.getPlayingTime());
        boardGame.setAge(boardGameRequestDto.getAge());
        boardGame.setMinPlayersNumber(boardGameRequestDto.getMinPlayersNumber());
        boardGame.setMaxPlayersNumber(boardGameRequestDto.getMaxPlayersNumber());
        boardGame.setComplexity(boardGameRequestDto.getComplexity());
        boardGame.setLanguageDependence(boardGameRequestDto.getLanguageDependence());
        boardGame.setDescription(new BoardGameDescription());
        boardGame.getDescription().setDescription(boardGameRequestDto.getDescription());
        boardGame.getDescription().setBoardGame(boardGame);
        return boardGame;
    }


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

    public static BoardGameResponseDto mapToBoardGameResponseDto(BoardGame boardGame, BoardGameDescription boardGameDescription) {
        return BoardGameResponseDto.builder()
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

    public static BoardGameResponseDto mapToBoardGameResponseDto(BoardGame boardGame) {
        return BoardGameResponseDto.builder()
                .id(boardGame.getId())
                .name(boardGame.getName())
                .playingTime(boardGame.getPlayingTime())
                .age(boardGame.getAge())
                .minPlayersNumber(boardGame.getMinPlayersNumber())
                .maxPlayersNumber(boardGame.getMaxPlayersNumber())
                .complexity(boardGame.getComplexity())
                .languageDependence(boardGame.getLanguageDependence())
                .description(boardGame.getDescription() != null ? boardGame.getDescription().getDescription() : "")
                .build();
    }

}
