package com.wj.bgtavern.exceptions.languagecode;

public class LanguageCodeNotFoundException extends RuntimeException {

    public LanguageCodeNotFoundException(Long id) {
        super("No such language code with id: " + id);
    }
}
