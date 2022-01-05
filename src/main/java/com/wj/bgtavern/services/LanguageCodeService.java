package com.wj.bgtavern.services;

import com.wj.bgtavern.exceptions.languagecode.LanguageCodeAlreadyExistsException;
import com.wj.bgtavern.exceptions.languagecode.LanguageCodeNotFoundException;
import com.wj.bgtavern.models.LanguageCode;
import com.wj.bgtavern.repositories.LanguageCodeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LanguageCodeService {

    private final LanguageCodeRepository languageCodeRepository;


    public List<LanguageCode> getLanguageCodes() {
        return languageCodeRepository.findAll();
    }

    public LanguageCode addLanguageCode(LanguageCode languageCode) {
        if (languageCodeRepository.existsByIsoLanguageName(languageCode.getIsoLanguageName())) {
            throw new LanguageCodeAlreadyExistsException(languageCode.getIsoLanguageName());
        }
        return languageCodeRepository.save(languageCode);
    }

    public LanguageCode editLanguageCode(LanguageCode languageCode) {
        if (!languageCodeRepository.existsById(languageCode.getId())) {
            throw new LanguageCodeNotFoundException(languageCode.getId());
        }
        if (languageCodeRepository.existsByIsoLanguageName(languageCode.getIsoLanguageName())) {
            throw new LanguageCodeAlreadyExistsException(languageCode.getIsoLanguageName());
        }
        return languageCodeRepository.save(languageCode);
    }

    public void deleteLanguageCode(Long id) {
        if (!languageCodeRepository.existsById(id)) {
            throw new LanguageCodeNotFoundException(id);
        }
        languageCodeRepository.deleteById(id);
    }
}
