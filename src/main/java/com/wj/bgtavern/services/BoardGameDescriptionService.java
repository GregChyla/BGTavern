package com.wj.bgtavern.services;

import com.wj.bgtavern.exceptions.boardgamedescription.BoardGameDescriptionAlreadyExistsException;
import com.wj.bgtavern.exceptions.boardgamedescription.BoardGameDescriptionNotFoundException;
import com.wj.bgtavern.models.BoardGameDescription;
import com.wj.bgtavern.models.dtos.BoardGameDescriptionDto;
import com.wj.bgtavern.models.dtos.BoardGameDescriptionRequestDto;
import com.wj.bgtavern.models.dtos.mappers.BoardGameDescriptionMapper;
import com.wj.bgtavern.repositories.BoardGameDescriptionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardGameDescriptionService {
    
    private final BoardGameDescriptionRepository boardGameDescriptionRepository;


    public BoardGameDescription getBoardGameDescription(Long id) {
        return boardGameDescriptionRepository.findById(id).orElseThrow(() -> new BoardGameDescriptionNotFoundException(id));
    }

    public BoardGameDescriptionDto addBoardGameDescription(BoardGameDescription boardGameDescription) {
        if (boardGameDescriptionRepository.existsById(boardGameDescription.getBoardGameId()))
            throw new BoardGameDescriptionAlreadyExistsException(boardGameDescription.getBoardGameId());

        return BoardGameDescriptionMapper.mapToBoardGameDescriptionDto(boardGameDescriptionRepository.save(boardGameDescription));
    }

    public BoardGameDescriptionDto editBoardGameDescription(Long boardGameId, BoardGameDescriptionRequestDto boardGameDescriptionRequestDto) {

        if (!boardGameDescriptionRepository.existsById(boardGameId))
            throw new BoardGameDescriptionNotFoundException(boardGameId);

        BoardGameDescription boardGameDescription = BoardGameDescriptionMapper.mapToBoardGameDescription(boardGameId, boardGameDescriptionRequestDto);

        return BoardGameDescriptionMapper.mapToBoardGameDescriptionDto(boardGameDescriptionRepository.save(boardGameDescription));
    }

    public BoardGameDescription editBoardGameDescription(BoardGameDescription boardGameDescription) {

        if (!boardGameDescriptionRepository.existsById(boardGameDescription.getBoardGameId()))
            throw new BoardGameDescriptionNotFoundException(boardGameDescription.getBoardGameId());

        return boardGameDescriptionRepository.save(boardGameDescription);
    }

    public void deleteBoardGameDescription(Long id) {
        if (!boardGameDescriptionRepository.existsById(id))
            throw new BoardGameDescriptionNotFoundException(id);

        boardGameDescriptionRepository.deleteById(id);
    }
}
