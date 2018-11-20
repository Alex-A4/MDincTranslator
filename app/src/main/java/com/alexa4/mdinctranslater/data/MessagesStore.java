package com.alexa4.mdinctranslater.data;


import java.util.ArrayList;

/**
 * Singleton class to store messages of dialogue
 * @author alexa4
 */
public class MessagesStore {
    //Singleton instance
    private static MessagesStore sStore;

    //List of messages
    private ArrayList<String> mMessages;


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

    private MessagesStore() {
        mMessages = new ArrayList<>();
        mLanguageFrom = new LanguageInfo("Русский", "ru");
        mLanguageTo = new LanguageInfo("Английский", "en");
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
}
