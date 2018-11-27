package com.alexa4.mdinctranslater.data;

import java.util.Collections;
import java.util.Comparator;

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

    /**
     * Getting comparator by languages names
     * @return the name alphabetic comparator
     */
    public static Comparator<LanguageInfo> getNameComparator() {
        return new Comparator<LanguageInfo>() {
            @Override
            public int compare(LanguageInfo o1, LanguageInfo o2) {
                return o1.getLanguageName().compareTo(o2.getLanguageName());
            }
        };
    }

    @Override
    public String toString() {
        return mLanguageName + " " + mLanguageCode;
    }
}
