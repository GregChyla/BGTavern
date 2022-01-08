package com.wj.bgtavern.models.dtos;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LanguageCodeResponseDto {
    private long id;
    private String isoLanguageName;
    private String iso6391;
    private String iso6392t;
    private String iso6392b;
}
