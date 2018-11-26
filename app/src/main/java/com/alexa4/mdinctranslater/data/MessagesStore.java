package com.alexa4.mdinctranslater.data;


import java.util.ArrayList;
import java.util.Arrays;

/**
 * Singleton class to store messages of dialogue
 * @author alexa4
 */
public class MessagesStore {
    //Singleton instance
    private static MessagesStore sStore;

    //List of messages
    private ArrayList<String> mMessages;

    //Pairs of languages
    private ArrayList<LanguageInfo> mPairs;


    //The language from which need translate
    private LanguageInfo mLanguageFrom;
    public LanguageInfo getLanguageFrom() {
        return mLanguageFrom;
    }
    public void setLanguageFrom(LanguageInfo languageFrom) {
        mLanguageFrom = languageFrom;
    }

    //The language to which need translate
    private LanguageInfo mLanguageTo;
    public LanguageInfo getLanguageTo() {
        return mLanguageTo;
    }
    public void setLanguageTo(LanguageInfo languageTo) {
        mLanguageTo = languageTo;
    }

    private MessagesStore() {
        mMessages = new ArrayList<>();
        mPairs = new ArrayList<>();
        fillList();
        mLanguageFrom = mPairs.get(0);
        mLanguageTo = mPairs.get(1);
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
     * Getting language by its name
     * @param name the name of language
     * @return the language or default lang (russian)
     */
    public LanguageInfo getLangByName(String name) {
        for (LanguageInfo info: mPairs)
            if (name.equals(info.getLanguageName()))
                return info;

        return mPairs.get(0);
    }


    public static MessagesStore getStore() {
        if (sStore == null)
            sStore = new MessagesStore();

        return sStore;
    }

    public ArrayList<String> getMessages() {
        return mMessages;
    }



    /**
     * Create the string pair of translated languages
     * for example en-ru
     * @return the codes pair
     */
    public String createLangToTranslate() {
        return mLanguageFrom.getLanguageCode() + "-" + mLanguageTo.getLanguageCode();
    }


    /**
     * Swapping languages which uses to translate
     */
    public void swapTargetLanguages() {
        LanguageInfo temp = mLanguageTo;
        mLanguageTo = mLanguageFrom;
        mLanguageFrom = temp;
    }
}
