package com.wj.bgtavern.controllers;

import com.wj.bgtavern.models.LanguageCode;
import com.wj.bgtavern.models.dtos.LanguageCodeRequestDto;
import com.wj.bgtavern.models.dtos.LanguageCodeResponseDto;
import com.wj.bgtavern.services.LanguageCodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class LanguageCodeController {

    private final LanguageCodeService languageCodeService;

    @GetMapping("/language_codes")
    public List<LanguageCodeResponseDto> getLanguageCodes() {
        return languageCodeService.getLanguageCodes();
    }

    @PostMapping("/language_codes")
    public LanguageCodeResponseDto addLanguageCode(@RequestBody @Valid LanguageCodeRequestDto languageCodeRequestDto) {
        return languageCodeService.addLanguageCode(languageCodeRequestDto);
    }

    @PutMapping("/language_codes/{id}")
    public LanguageCodeResponseDto editLanguageCode(@PathVariable Long id, @RequestBody @Valid LanguageCodeRequestDto languageCodeRequestDto) {
        return languageCodeService.editLanguageCode(languageCodeRequestDto, id);
    }

    @DeleteMapping("/language_codes/{id}")
    public void deleteLanguageCode(@PathVariable Long id) {
        languageCodeService.deleteLanguageCode(id);
    }
}
