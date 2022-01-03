package com.wj.bgtavern.repositories;

import com.wj.bgtavern.models.BoardGameDescription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardGameDescriptionRepository extends JpaRepository<BoardGameDescription, Long> {
}
