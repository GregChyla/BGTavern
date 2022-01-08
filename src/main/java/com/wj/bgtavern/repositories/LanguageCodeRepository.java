package com.wj.bgtavern.repositories;

import com.wj.bgtavern.models.LanguageCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface LanguageCodeRepository extends JpaRepository<LanguageCode, Long> {

    boolean existsByIsoLanguageName(String isoLanguageName);

    @Query("select case when count(lc) > 0 then true else false end from LanguageCode lc " +
            "where lc.id <> :id and lc.isoLanguageName = :isoLanguageName")
    boolean existsByIsoLanguageNameAndNotWithId(String isoLanguageName, Long id);
}
