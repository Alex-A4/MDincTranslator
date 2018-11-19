package com.alexa4.mdinctranslater.data;

import java.util.ArrayList;

/**
 * Singleton store to collect Yandex languages pairs
 * @author alexa4
 */
public class LanguagesStore {
    //Pairs of languages
    private ArrayList<LanguageInfo> mPairs;

    //The language from which need translate
    private LanguageInfo mLanguageFrom;
    public LanguageInfo getLanguageFrom() {
        return mLanguageFrom;
    }

    //The language to which need translate
    private LanguageInfo mLanguageTo;
    public LanguageInfo getLanguageTo() {
        return mLanguageTo;
    }

    //Singleton instance of store
    private static LanguagesStore sStore;

    private LanguagesStore() {
        mPairs = new ArrayList<>();
        mLanguageFrom = new LanguageInfo("Русский", "ru");
        mLanguageTo = new LanguageInfo("Английский", "en");
    }

    public static LanguagesStore getStore() {
        if (sStore == null)
            sStore = new LanguagesStore();

        return sStore;
    }

    public ArrayList<LanguageInfo> getPairs() {
        return mPairs;
    }


    /**
     * Swap the target languages
     */
    public void swapTargetLanguages() {
        LanguageInfo temp = mLanguageFrom;
        mLanguageFrom = mLanguageTo;
        mLanguageTo = temp;
    }

    /**
     * Create the string pair of translated languages
     * for example en-ru
     * @return the codes pair
     */
    public String createLangToTranslate() {
        return mLanguageFrom.getLanguageCode() + "-" + mLanguageTo.getLanguageCode();
    }
}
