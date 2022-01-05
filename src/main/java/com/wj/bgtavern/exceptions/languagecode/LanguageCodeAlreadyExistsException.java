package com.wj.bgtavern.exceptions.languagecode;

public class LanguageCodeAlreadyExistsException extends RuntimeException{

    public LanguageCodeAlreadyExistsException(String isoLanguageName) {
        super("Language code " + isoLanguageName + " already exists in database");
    }
}
