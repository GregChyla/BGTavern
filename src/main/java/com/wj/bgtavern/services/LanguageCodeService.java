package com.wj.bgtavern.services;

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
            return new LanguageCode();
        }
        return languageCodeRepository.save(languageCode);
    }

    public LanguageCode editLanguageCode(LanguageCode languageCode) {
        if (!languageCodeRepository.existsById(languageCode.getId())) {
            return new LanguageCode();
        }
        if (languageCodeRepository.existsByIsoLanguageName(languageCode.getIsoLanguageName()))
        {
            return new LanguageCode();
        }
        return languageCodeRepository.save(languageCode);
    }

    public void deleteLanguageCode(Long id) {
        if (!languageCodeRepository.existsById(id)) {
            return;
        }
        languageCodeRepository.deleteById(id);
    }
}
