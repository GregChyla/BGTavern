package com.wj.bgtavern.services;

import com.wj.bgtavern.models.LanguageCode;
import com.wj.bgtavern.models.dtos.LanguageCodeResponseDto;
import com.wj.bgtavern.repositories.LanguageCodeRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LanguageCodeServiceTest {

    @InjectMocks
    private LanguageCodeService languageCodeService;

    @Mock
    private LanguageCodeRepository languageCodeRepository;

    @Test
    @DisplayName("should return list of empty LanguageCodeResponseDto")
    void shouldReturnListOfEmptyLanguageCodeResponseDto() {
        //given
        var emptyLanguageCode = prepareEmptyTestLanguageCode();
        doReturn(List.of(emptyLanguageCode)).when(languageCodeRepository).findAll();

        //when
        List<LanguageCodeResponseDto> languageCodes = assertDoesNotThrow(() -> languageCodeService.getLanguageCodes());

        //then
        verify(languageCodeRepository).findAll();
        assertAll(
                () -> assertThat(languageCodes).hasSize(1),
                () -> assertThat(languageCodes.get(0)).isEqualToComparingFieldByFieldRecursively(new LanguageCode())
        );
    }

    @Test
    @DisplayName("should return list of empty LanguageCodeResponseDto")
    void shouldReturnListOfLanguageCodeResponseDtoWithValues() {
        //given
        var languageCode = prepareTestLanguageCodeWIthValues();
        doReturn(List.of(languageCode)).when(languageCodeRepository).findAll();

        //when
        List<LanguageCodeResponseDto> languageCodes = assertDoesNotThrow(() -> languageCodeService.getLanguageCodes());

        //then
        verify(languageCodeRepository).findAll();
        assertAll(
                () -> assertThat(languageCodes).hasSize(1),
                () -> assertThat(languageCodes.get(0).getId()).isEqualTo(1L),
                () -> assertThat(languageCodes.get(0).getIsoLanguageName()).isEqualTo("some name"),
                () -> assertThat(languageCodes.get(0).getIso6391()).isEqualTo("a"),
                () -> assertThat(languageCodes.get(0).getIso6392b()).isEqualTo("b"),
                () -> assertThat(languageCodes.get(0).getIso6392t()).isEqualTo("t")
        );
    }

    @Test
    @DisplayName("should throw exception when accessing DB")
    void shouldThrowExceptionWhenAccessingDb() {
        //given
        var languageCode = prepareTestLanguageCodeWIthValues();
        doThrow(new IllegalAccessError("DB access error")).when(languageCodeRepository).findAll();

        //when
        assertThatThrownBy(() -> languageCodeService.getLanguageCodes())
                .isInstanceOf(IllegalAccessError.class)
                .hasMessage("DB access error");

        //then
        verify(languageCodeRepository).findAll();
    }

    private LanguageCode prepareEmptyTestLanguageCode() {
        return new LanguageCode();
    }

    private LanguageCode prepareTestLanguageCodeWIthValues() {
        var languageCode = new LanguageCode();
        languageCode.setId(1L);
        languageCode.setIsoLanguageName("some name");
        languageCode.setIso6391("a");
        languageCode.setIso6392b("b");
        languageCode.setIso6392t("t");
        return languageCode;
    }
}
