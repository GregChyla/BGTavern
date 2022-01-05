package com.wj.bgtavern.services;

import com.wj.bgtavern.exceptions.boardgame.BoardGameAlreadyExistsException;
import com.wj.bgtavern.exceptions.boardgame.BoardGameNotFoundException;
import com.wj.bgtavern.models.BoardGame;
import com.wj.bgtavern.models.BoardGameDescription;
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

    private final BoardGameRepository boardGameRepository;
    private final BoardGameDescriptionRepository boardGameDescriptionRepository;


    public List<BoardGame> getBoardGames(int pageNumber, int pageSize) {
        return boardGameRepository.findAllBoardGames(
                PageRequest.of(pageNumber, pageSize));
    }

    public BoardGame getSingleBoardGame(Long id) {
        // TODO: https://www.baeldung.com/jpa-optimistic-locking, przerobić wszystkie endpointy które wymagaja lockingu pod to
        BoardGame boardGame = boardGameRepository.findById(id)
                .orElseThrow(() -> new BoardGameNotFoundException(id));
        BoardGameDescription description = boardGameDescriptionRepository.findById(id).orElse(null);
        boardGame.setDescription(description);
        return boardGame;
    }

    public BoardGame addBoardGame(BoardGame boardGame, BoardGameDescription description) {
        if (boardGameRepository.existsByName(boardGame.getName())) {
            throw new BoardGameAlreadyExistsException(boardGame.getName());
        }
        boardGameRepository.save(boardGame);
        description.setBoardGameId(boardGame.getId());
        boardGameDescriptionRepository.save(description);
        boardGame.setDescription(description);
        return boardGame;
    }

    public BoardGame editBoardGame(BoardGame boardGame, BoardGameDescription description) {
        if (!boardGameRepository.existsById(boardGame.getId())) {
            throw new BoardGameNotFoundException(boardGame.getId());
        }
        if (boardGameRepository.existsByName(boardGame.getName())) {
            throw new BoardGameAlreadyExistsException(boardGame.getName());
        }
        boardGameRepository.save(boardGame);
        boardGameDescriptionRepository.save(description);
        boardGame.setDescription(description);
        return boardGame;
    }

    @Transactional
    public void deleteBoardGame(Long id) {
        if (!boardGameRepository.existsById(id)) {
            throw new BoardGameNotFoundException(id);
        }
        if (boardGameDescriptionRepository.existsById(id))
            boardGameDescriptionRepository.deleteById(id);
        boardGameRepository.deleteById(id);
    }
}
