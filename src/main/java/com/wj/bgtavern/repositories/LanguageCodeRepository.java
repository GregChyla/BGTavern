package com.wj.bgtavern.repositories;

import com.wj.bgtavern.models.LanguageCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LanguageCodeRepository extends JpaRepository<LanguageCode, Long> {

    boolean existsByIsoLanguageName(String isoLanguageName);
}
