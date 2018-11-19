package com.alexa4.mdinctranslater.presenters;

import com.alexa4.mdinctranslater.network.YandexTranslator;
import com.alexa4.mdinctranslater.ui.TranslatorFragment;

public class TranslatorPresenter {
    private TranslatorFragment mView;


    /**
     * Initialize presenter by the view
     */
    public TranslatorPresenter(TranslatorFragment view) {
        mView = view;
    }

    /**
     * Detach the view from presenter
     */
    public void detach() {
        mView = null;
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
}
