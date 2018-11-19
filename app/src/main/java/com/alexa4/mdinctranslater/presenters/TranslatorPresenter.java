package com.alexa4.mdinctranslater.presenters;

import com.alexa4.mdinctranslater.data.LanguagesStore;
import com.alexa4.mdinctranslater.network.YandexTranslator;
import com.alexa4.mdinctranslater.ui.TranslatorFragment;

public class TranslatorPresenter {
    private TranslatorFragment mView;
    private LanguagesStore mStore;


    /**
     * Initialize presenter by the view
     */
    public TranslatorPresenter(TranslatorFragment view) {
        mView = view;
        mStore = LanguagesStore.getStore();
    }

    /**
     * Detach the view from presenter
     */
    public void detach() {
        mView = null;
        mStore = null;
    }


    /**
     * Get the translated text and return it to view
     * @param text the text which need translate
     */
    public void getTranslatedText(String text) {
        YandexTranslator.translateText(text, "ru-en",
                new YandexTranslator.TranslateCallback() {
                    @Override
                    public void sendTranslatedText(String translatedText) {
                        mView.setTranslatedText(translatedText);
                    }
                });
    }

    /**
     * Swap the target languages
     */
    public void swapTargetLanguages() {
        mStore.swapTargetLanguages();
    }

    /**
     * Get the name of language from which need translate
     * @return
     */
    public String getLanguageNameOfFromText() {
        return mStore.getLanguageFrom().getLanguageName();
    }


    /**
     * Get the name of language to which need translate
     * @return
     */
    public String getLanguageNameOfToText() {
        return mStore.getLanguageTo().getLanguageName();
    }
}
