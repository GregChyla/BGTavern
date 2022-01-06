package com.wj.bgtavern.services;

import com.wj.bgtavern.exceptions.boardgame.BoardGameAlreadyExistsException;
import com.wj.bgtavern.exceptions.boardgame.BoardGameNotFoundException;
import com.wj.bgtavern.exceptions.boardgamedescription.BoardGameDescriptionAlreadyExistsException;
import com.wj.bgtavern.models.BoardGame;
import com.wj.bgtavern.models.BoardGameDescription;
import com.wj.bgtavern.models.dtos.BoardGameDto;
import com.wj.bgtavern.models.dtos.BoardGameHeaderDto;
import com.wj.bgtavern.models.dtos.mappers.BoardGameDescriptionMapper;
import com.wj.bgtavern.models.dtos.mappers.BoardGameDtoMapper;
import com.wj.bgtavern.models.dtos.mappers.BoardGameHeaderDtoMapper;
import com.wj.bgtavern.models.dtos.mappers.BoardGameMapper;
import com.wj.bgtavern.repositories.BoardGameDescriptionRepository;
import com.wj.bgtavern.repositories.BoardGameRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

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
        return BoardGameHeaderDtoMapper.mapToBoardGameHeaderDtos(boardGames);
    }

//    public List<BoardGame> getBoardGameHeaders(int pageNumber, int pageSize) {
//        return boardGameRepository.findAllBoardGames(PageRequest.of(pageNumber, pageSize));
//    }

    public BoardGameDto getSingleBoardGame(Long id) {
        BoardGame boardGame = boardGameRepository.findById(id).orElseThrow(() -> new BoardGameNotFoundException(id));
        BoardGameDescription description = boardGameDescriptionService.getBoardGameDescription(id);

        return BoardGameDtoMapper.mapToBoardGameDto(boardGame, description);
    }

//    public BoardGame getSingleBoardGame(Long id) {
//        BoardGame boardGame = boardGameRepository.findById(id).orElseThrow(() -> new BoardGameNotFoundException(id));
//        return boardGame;
//    }

    public BoardGameDto addBoardGame(BoardGameDto boardGameDto) {
        if (boardGameRepository.existsByName(boardGameDto.getName()))
            throw new BoardGameAlreadyExistsException(boardGameDto.getName());
        BoardGame boardGame = BoardGameMapper.mapToBoardGame(boardGameDto);
        boardGameRepository.save(boardGame);
        BoardGameDescription boardGameDescription = BoardGameDescriptionMapper.mapToBoardGameDescription(boardGame, boardGameDto);
        boardGameDescriptionService.addBoardGameDescription(boardGameDescription);
        return boardGameDto;
    }

//    public BoardGame addBoardGame(BoardGame boardGame) {
//        if (boardGameRepository.existsByName(boardGame.getName()))
//            throw new BoardGameAlreadyExistsException(boardGame.getName());
//
//        boardGameRepository.save(boardGame);
//        return boardGame;
//    }

    public BoardGameDto editBoardGame(Long id, BoardGameDto boardGameDto) {
        if (!boardGameRepository.existsById(id))
            throw new BoardGameNotFoundException(id);
        System.out.println("-------------------------------------------------------------");
        if (boardGameRepository.existsByNameAndNotWithId(boardGameDto.getName(), id))
            throw new BoardGameAlreadyExistsException(boardGameDto.getName());
        System.out.println("-------------------------------------------------------------");

        BoardGame boardGame = BoardGameMapper.mapToBoardGame(id, boardGameDto);
        boardGameRepository.save(boardGame);
        BoardGameDescription boardGameDescription = BoardGameDescriptionMapper.mapToBoardGameDescription(id, boardGameDto);
        boardGameDescriptionService.editBoardGameDescription(boardGameDescription);

        return boardGameDto;
    }

//    public BoardGame editBoardGame(BoardGame boardGame, BoardGameDescription description) {
//        if (!boardGameRepository.existsById(boardGame.getId()))
//            throw new BoardGameNotFoundException(boardGame.getId());
//        if (boardGameRepository.existsByName(boardGame.getName()))
//            throw new BoardGameAlreadyExistsException(boardGame.getName());
//
//        boardGameRepository.save(boardGame);
//        description.setBoardGameId(boardGame.getId());
////        boardGameDescriptionRepository.save(description);
//        return boardGame;
//    }

    @Transactional
    public void deleteBoardGame(Long id) {
        if (!boardGameRepository.existsById(id))
            throw new BoardGameNotFoundException(id);

        boardGameDescriptionService.deleteBoardGame(id);
        boardGameRepository.deleteById(id);
    }
}
