package com.wj.bgtavern.services;

import com.wj.bgtavern.exceptions.boardgame.BoardGameAlreadyExistsException;
import com.wj.bgtavern.exceptions.boardgame.BoardGameNotFoundException;
import com.wj.bgtavern.models.BoardGame;
import com.wj.bgtavern.models.BoardGameDescription;
import com.wj.bgtavern.models.dtos.BoardGameRequestDto;
import com.wj.bgtavern.models.dtos.BoardGameResponseDto;
import com.wj.bgtavern.models.dtos.BoardGameHeaderDto;
import com.wj.bgtavern.models.dtos.mappers.BoardGameDescriptionMapper;
import com.wj.bgtavern.models.dtos.mappers.BoardGameMapper;
import com.wj.bgtavern.repositories.BoardGameRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.persistence.LockModeType;
import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardGameService {

    // TODO: https://www.baeldung.com/jpa-optimistic-locking, przerobić wszystkie endpointy które wymagaja lockingu pod to

    private final BoardGameRepository boardGameRepository;
    private final BoardGameDescriptionService boardGameDescriptionService;


    public List<BoardGameHeaderDto> getBoardGameHeaders(int pageNumber, int pageSize) {
        List<BoardGame> boardGames = boardGameRepository.findAllBoardGames(PageRequest.of(pageNumber, pageSize));
        return BoardGameMapper.mapToBoardGameHeaderDtos(boardGames);
    }

    public BoardGameResponseDto getSingleBoardGame(Long id) {
        BoardGame boardGame = boardGameRepository.findById(id).orElseThrow(() -> new BoardGameNotFoundException(id));
        BoardGameDescription description = boardGameDescriptionService.getBoardGameDescription(id);
        return BoardGameMapper.mapToBoardGameResponseDto(boardGame, description);
    }

    public BoardGameResponseDto addBoardGame(BoardGameRequestDto boardGameRequestDto) {
        if (boardGameRepository.existsByName(boardGameRequestDto.getName()))
            throw new BoardGameAlreadyExistsException(boardGameRequestDto.getName());
        BoardGame boardGame = BoardGameMapper.mapToBoardGame(boardGameRequestDto);
        boardGameRepository.save(boardGame);
        BoardGameDescription boardGameDescription = BoardGameDescriptionMapper.mapToBoardGameDescription(boardGame, boardGameRequestDto);
        boardGameDescriptionService.addBoardGameDescription(boardGameDescription);
        BoardGameResponseDto boardGameResponseDto = BoardGameMapper.mapToBoardGameResponseDto(boardGame, boardGameDescription);
        return boardGameResponseDto;
    }

    @Transactional
    public BoardGameResponseDto editBoardGame(Long id, BoardGameRequestDto boardGameRequestDto) {
        if (!boardGameRepository.existsById(id))
            throw new BoardGameNotFoundException(id);
        if (boardGameRepository.existsByNameAndNotWithId(boardGameRequestDto.getName(), id))
            throw new BoardGameAlreadyExistsException(boardGameRequestDto.getName());

        BoardGame boardGame = BoardGameMapper.mapToBoardGame(id, boardGameRequestDto);
        boardGameRepository.save(boardGame);

        //TODO: Tutaj się coś sypie, poczytać o tym: https://www.baeldung.com/spring-data-jpa-dynamicupdate?fbclid=IwAR1_kYfxz9_-6c25Eas-lplhN-1p74YV_OD9oUuRw2BMezoJnXRkQQir_H0
        //TODO: albo o optimistic i pesimistic locking
        BoardGameDescription boardGameDescription = BoardGameDescriptionMapper.mapToBoardGameDescription(boardGame, boardGameRequestDto);
        boardGameDescriptionService.editBoardGameDescription(boardGameDescription);
        BoardGameResponseDto boardGameResponseDto = BoardGameMapper.mapToBoardGameResponseDto(boardGame, boardGameDescription);
        return boardGameResponseDto;
    }

//    @Transactional
    public void deleteBoardGame(Long id) {
        if (!boardGameRepository.existsById(id))
            throw new BoardGameNotFoundException(id);
//        boardGameDescriptionService.deleteBoardGameDescription(id);
        boardGameRepository.deleteById(id);
    }
}
