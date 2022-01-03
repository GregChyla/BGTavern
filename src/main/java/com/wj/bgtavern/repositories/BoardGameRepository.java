package com.wj.bgtavern.repositories;

import com.wj.bgtavern.models.BoardGame;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardGameRepository extends JpaRepository<BoardGame, Long> {

    @Query("select bg from BoardGame bg")
    List<BoardGame> findAllBoardGames(Pageable page);

    BoardGame findByName(String name);

    boolean existsByName(String name);
}
