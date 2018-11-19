package com.alexa4.mdinctranslater.data;

import java.util.ArrayList;

/**
 * Singleton store to collect Yandex languages pairs
 * @author alexa4
 */
public class LanguagesStore {
    //Pairs of languages
    private ArrayList<LanguageInfo> mPairs;

    //Singleton instance of store
    private static LanguagesStore sStore;

    private LanguagesStore() {
        mPairs = new ArrayList<>();
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
     * Create the string pair of translated languages
     * for example en-ru
     * @param langFrom the language from which need translate
     * @param langTo the language to which need translate
     * @return the codes pair
     */
    public String createLangToTranslate(LanguageInfo langFrom, LanguageInfo langTo) {
        return langFrom.getLanguageCode() + "-" + langTo.getLanguageCode();
    }
}
