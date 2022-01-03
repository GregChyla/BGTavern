package com.wj.bgtavern.services;

import com.wj.bgtavern.models.BoardGame;
import com.wj.bgtavern.models.BoardGameDescription;
import com.wj.bgtavern.repositories.BoardGameDescriptionRepository;
import com.wj.bgtavern.repositories.BoardGameRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

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
        BoardGame boardGame = boardGameRepository.findById(id).orElse(new BoardGame());
        BoardGameDescription description = boardGameDescriptionRepository.findById(id).orElse(null);
        boardGame.setDescription(description);
        return boardGame;
    }

    public BoardGame addBoardGame(BoardGame boardGame, BoardGameDescription description) {
        if (boardGameRepository.existsByName(boardGame.getName())) {
            // TODO: throw exception if trying to insert duplicate key row for column name
            return new BoardGame();
        }
        boardGameRepository.save(boardGame);
        description.setBoardGameId(boardGame.getId());
        boardGameDescriptionRepository.save(description);
        boardGame.setDescription(description);
        return boardGame;
    }

    public BoardGame editBoardGame(BoardGame boardGame, BoardGameDescription description) {
        if (!boardGameRepository.existsById(boardGame.getId())) {
            // TODO: throw exception if trying to update record which is not in database
            return new BoardGame();
        }
        if (boardGameRepository.existsByName(boardGame.getName()))
        {
            // TODO: throw exception if trying to create by update duplicate key row for column name
            return new BoardGame();
        }
        boardGameRepository.save(boardGame);
        boardGameDescriptionRepository.save(description);
        boardGame.setDescription(description);
        return boardGame;
    }

    @Transactional
    public void deleteBoardGame(Long id) {
        if (!boardGameRepository.existsById(id))
        {
            // TODO: throw exception if trying to delete record which is not in database
            return;
        }
        if (boardGameDescriptionRepository.existsById(id))
            boardGameDescriptionRepository.deleteById(id);
        boardGameRepository.deleteById(id);
    }
}
