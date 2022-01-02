package com.wj.bgtavern.service;

import com.wj.bgtavern.model.BoardGame;
import com.wj.bgtavern.repository.BoardGameDescriptionRepository;
import com.wj.bgtavern.repository.BoardGameRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardGameService {

    private final BoardGameRepository boardGameRepository;
    private final BoardGameDescriptionRepository boardGameDescriptionRepository;


    public List<BoardGame> getBoardGames(int pageNumber, int pageSize) {
        return boardGameRepository.findAllBoardGames(
                PageRequest.of(pageNumber, pageSize));
    }

    public BoardGame getSingleBoardGame(Long id) {
        return boardGameRepository.findById(id).orElseThrow();
    }
}
