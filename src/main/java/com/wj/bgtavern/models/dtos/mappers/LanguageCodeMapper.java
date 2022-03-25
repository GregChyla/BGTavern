package com.wj.bgtavern.models.dtos.mappers;

import com.wj.bgtavern.models.LanguageCode;
import com.wj.bgtavern.models.dtos.LanguageCodeRequestDto;
import com.wj.bgtavern.models.dtos.LanguageCodeResponseDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class LanguageCodeMapper {

    public static List<LanguageCodeResponseDto> mapToLanguageCodeResponseDtos(List<LanguageCode> languageCodes){
        return languageCodes.stream()
                .map(LanguageCodeMapper::mapToLanguageCodeResponseDto)
                .collect(Collectors.toList());
    }

    public static LanguageCodeResponseDto mapToLanguageCodeResponseDto(LanguageCode languageCode) {
        return LanguageCodeResponseDto.builder()
                .id(languageCode.getId())
                .isoLanguageName(languageCode.getIsoLanguageName())
                .iso6391(languageCode.getIso6391())
                .iso6392b(languageCode.getIso6392b())
                .iso6392t(languageCode.getIso6392t())
                .build();
    }

    public static LanguageCode mapToLanguageCode(Long id, LanguageCodeRequestDto languageCodeRequestDto) {
        LanguageCode languageCode = mapToLanguageCode(languageCodeRequestDto);
        languageCode.setId(id);
        return languageCode;
    }

    public static LanguageCode mapToLanguageCode(LanguageCodeRequestDto languageCodeRequestDto) {
        LanguageCode languageCode = new LanguageCode();
        languageCode.setIsoLanguageName(languageCodeRequestDto.getIsoLanguageName());
        languageCode.setIso6391(languageCodeRequestDto.getIso6391());
        languageCode.setIso6392b(languageCodeRequestDto.getIso6392b());
        languageCode.setIso6392t(languageCodeRequestDto.getIso6392t());
        return languageCode;
    }
}
