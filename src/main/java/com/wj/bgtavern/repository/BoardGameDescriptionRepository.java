package com.wj.bgtavern.repository;

import com.wj.bgtavern.model.BoardGameDescription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardGameDescriptionRepository extends JpaRepository<BoardGameDescription, Long> {
}
