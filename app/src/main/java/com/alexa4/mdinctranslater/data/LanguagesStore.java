package com.alexa4.mdinctranslater.data;

import java.util.ArrayList;
import java.util.Arrays;

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
        fillList();
        mLanguageFrom = mPairs.get(0);
        mLanguageTo = mPairs.get(1);
    }

    public static LanguagesStore getStore() {
        if (sStore == null)
            sStore = new LanguagesStore();

        return sStore;
    }

    public ArrayList<LanguageInfo> getPairs() {
        return mPairs;
    }


    private void fillList() {
        mPairs.addAll(
                Arrays.asList(
                new LanguageInfo("Русский", "ru"),
                new LanguageInfo("Английский", "en"),
                new LanguageInfo("Украинский", "uk"),
                new LanguageInfo("Японский", "ja"),
                new LanguageInfo("Малайсийский", "ms"),
                new LanguageInfo("Корейский", "ko"),
                new LanguageInfo("Китайский", "zh"),
                new LanguageInfo("Казахский", "kk"),
                new LanguageInfo("Испанский", "es"),
                new LanguageInfo("Итальянский", "it"),
                new LanguageInfo("Узбекский", "uz"),
                new LanguageInfo("Финский", "fi"),
                new LanguageInfo("Исландский", "is"),
                new LanguageInfo("Датский", "da"),
                new LanguageInfo("Французский", "fr"),
                new LanguageInfo("Шведский", "sv"),
                new LanguageInfo("Чешский", "cs"))
        );
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
