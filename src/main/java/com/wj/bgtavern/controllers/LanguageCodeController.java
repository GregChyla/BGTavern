package com.wj.bgtavern.controllers;

import com.wj.bgtavern.models.LanguageCode;
import com.wj.bgtavern.services.LanguageCodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class LanguageCodeController {

    private final LanguageCodeService languageCodeService;

    @GetMapping("/language_codes")
    public List<LanguageCode> getLanguageCodes() {
        return languageCodeService.getLanguageCodes();
    }

    @PostMapping("/language_codes")
    public LanguageCode addLanguageCode(@RequestBody LanguageCode languageCode) {
        return languageCodeService.addLanguageCode(languageCode);
    }

    @PutMapping("/language_codes/{id}")
    public LanguageCode editLanguageCode(@PathVariable Long id, @RequestBody LanguageCode languageCode) {
        languageCode.setId(id);
        return languageCodeService.editLanguageCode(languageCode);
    }

    @DeleteMapping("/language_codes/{id}")
    public void deleteLanguageCode(@PathVariable Long id) {
        languageCodeService.deleteLanguageCode(id);
    }
}
