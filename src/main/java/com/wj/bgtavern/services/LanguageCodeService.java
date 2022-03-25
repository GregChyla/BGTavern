package com.wj.bgtavern.services;

import com.wj.bgtavern.exceptions.languagecode.LanguageCodeAlreadyExistsException;
import com.wj.bgtavern.exceptions.languagecode.LanguageCodeNotFoundException;
import com.wj.bgtavern.models.LanguageCode;
import com.wj.bgtavern.models.dtos.LanguageCodeRequestDto;
import com.wj.bgtavern.models.dtos.LanguageCodeResponseDto;
import com.wj.bgtavern.models.dtos.mappers.LanguageCodeMapper;
import com.wj.bgtavern.repositories.LanguageCodeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LanguageCodeService {

    private final LanguageCodeRepository languageCodeRepository;

    public List<LanguageCodeResponseDto> getLanguageCodes() {
        return LanguageCodeMapper.mapToLanguageCodeResponseDtos(languageCodeRepository.findAll());
    }

    public LanguageCodeResponseDto addLanguageCode(LanguageCodeRequestDto languageCodeRequestDto) {
        if (languageCodeRepository.existsByIsoLanguageName(languageCodeRequestDto.getIsoLanguageName()))
            throw new LanguageCodeAlreadyExistsException(languageCodeRequestDto.getIsoLanguageName());

        LanguageCode languageCode = languageCodeRepository.save(
                LanguageCodeMapper.mapToLanguageCode(languageCodeRequestDto));
        return LanguageCodeMapper.mapToLanguageCodeResponseDto(languageCode);
    }

    public LanguageCodeResponseDto editLanguageCode(LanguageCodeRequestDto languageCodeRequestDto, Long id) {
        if (!languageCodeRepository.existsById(id))
            throw new LanguageCodeNotFoundException(id);
        if (languageCodeRepository.existsByIsoLanguageNameAndNotWithId(languageCodeRequestDto.getIsoLanguageName(), id))
            throw new LanguageCodeAlreadyExistsException(languageCodeRequestDto.getIsoLanguageName());

        LanguageCode languageCode = languageCodeRepository.save(
                LanguageCodeMapper.mapToLanguageCode(id, languageCodeRequestDto));
        return LanguageCodeMapper.mapToLanguageCodeResponseDto(languageCode);
    }

    public void deleteLanguageCode(Long id) {
        if (!languageCodeRepository.existsById(id))
            throw new LanguageCodeNotFoundException(id);

        languageCodeRepository.deleteById(id);
    }
}
