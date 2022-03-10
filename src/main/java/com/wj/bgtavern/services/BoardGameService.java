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


    private final BoardGameRepository boardGameRepository;
    private final BoardGameDescriptionService boardGameDescriptionService;


    public List<BoardGameHeaderDto> getBoardGameHeaders(int pageNumber, int pageSize) {
        List<BoardGameHeaderDto> boardGameHeaderDtos = boardGameRepository.findAllBoardGameHeaders(PageRequest.of(pageNumber, pageSize));
        return boardGameHeaderDtos;
    }

    public BoardGameResponseDto getSingleBoardGame(Long id) {
        if (!boardGameRepository.existsById(id))
            throw new BoardGameNotFoundException(id);
        BoardGame boardGame = boardGameRepository.findByIdWithDescription(id);
        return BoardGameMapper.mapToBoardGameResponseDto(boardGame);
    }

    public BoardGameResponseDto addBoardGame(BoardGameRequestDto boardGameRequestDto) {
        if (boardGameRepository.existsByName(boardGameRequestDto.getName()))
            throw new BoardGameAlreadyExistsException(boardGameRequestDto.getName());
        BoardGame boardGame = BoardGameMapper.mapToBoardGame(boardGameRequestDto);
        boardGameRepository.save(boardGame);
        BoardGameResponseDto boardGameResponseDto = BoardGameMapper.mapToBoardGameResponseDto(boardGame);
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

//        BoardGameDescription boardGameDescription = BoardGameDescriptionMapper.mapToBoardGameDescription(boardGame, boardGameRequestDto);
//        boardGameDescriptionService.editBoardGameDescription(boardGameDescription);
        BoardGameResponseDto boardGameResponseDto = BoardGameMapper.mapToBoardGameResponseDto(boardGame);
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
