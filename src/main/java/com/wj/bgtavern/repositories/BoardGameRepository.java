package com.wj.bgtavern.repositories;

import com.wj.bgtavern.models.BoardGame;
import com.wj.bgtavern.models.dtos.BoardGameHeaderDto;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.LockModeType;
import java.util.List;

@Repository
public interface BoardGameRepository extends JpaRepository<BoardGame, Long> {

    @Query("select bg from BoardGame bg")
    List<BoardGame> findAllBoardGames(Pageable page);

    @Query("SELECT new com.wj.bgtavern.models.dtos.BoardGameHeaderDto(bg.id, bg.name) FROM BoardGame bg")
    List<BoardGameHeaderDto> findAllBoardGameHeaders(Pageable page);

    BoardGame findByName(String name);

    boolean existsByName(String name);

    @Query("select case when count(bg) > 0 then true else false end from BoardGame bg where bg.id <> :id and bg.name = :name")
    boolean existsByNameAndNotWithId(String name, Long id);

}
