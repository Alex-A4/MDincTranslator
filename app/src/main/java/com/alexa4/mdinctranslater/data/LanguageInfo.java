package com.alexa4.mdinctranslater.data;

/**
 * Contains pair like: language - language code
 * for example english-en
 * @author alexa4
 */
public class LanguageInfo {
    //The name of language, english
    private final String mLanguageName;
    //The code of language, en
    private final String mLanguageCode;

    public LanguageInfo(String languageName, String languageCode) {
        mLanguageName = languageName;
        mLanguageCode = languageCode;
    }

    public String getLanguageName() {
        return mLanguageName;
    }

    public String getLanguageCode() {
        return mLanguageCode;
    }
}
