package com.alexa4.mdinctranslater.presenters;


import com.alexa4.mdinctranslater.data.MessagesStore;
import com.alexa4.mdinctranslater.ui.DialogFragment;

import java.util.ArrayList;

/**
 * Presenter which connect DialogFragment and data
 */
public class DialogPresenter {
    private DialogFragment mView;
    private MessagesStore mStore;

    /**
     * Initialize presenter by View Fragment
     * @param dialogFragment
     */
    public DialogPresenter(DialogFragment dialogFragment) {
        mView = dialogFragment;
        mStore = MessagesStore.getStore();
    }

    /**
     * Detach view from presenter
     */
    public void detach() {
        mView = null;
        mStore = null;
    }

    /**
     * Getting list of messages
     * @return the messages
     */
    public ArrayList<String> getMessages() {
        return mStore.getMessages();
    }


    /**
     * Build the string of translated languages
     * for example Russian - English
     * @return the languages subtitle
     */
    public String getTranslateLanguagesSubtitle() {
        return mStore.getLanguageFrom().getLanguageName()
                + " - " + mStore.getLanguageTo().getLanguageName();
    }

    public String getNameOfLanguageFrom() {
        return mStore.getLanguageFrom().getLanguageName();
    }

    /**
     * Swapping languages
     */
    public void swapTargetLanguages() {
        mStore.swapTargetLanguages();
    }

    /**
     * String get languages pair for translator
     * @return
     */
    public String getLangsPairForTranslator() {
        return mStore.createLangToTranslate();
    }
}
