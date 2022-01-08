package com.wj.bgtavern.models.dtos;

import lombok.Builder;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Builder
public class LanguageCodeRequestDto {
    @NotBlank @Length(min=2)
    private String isoLanguageName;
    @NotNull @Length(min=2,max=2)
    private String iso6391;
    @Length(min=3,max=3)
    private String iso6392t;
    @Length(min=3,max=3)
    private String iso6392b;
}
