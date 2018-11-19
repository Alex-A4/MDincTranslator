package com.alexa4.mdinctranslater.presenters;


import com.alexa4.mdinctranslater.data.MessagesStore;
import com.alexa4.mdinctranslater.ui.DialogFragment;

import java.util.ArrayList;

/**
 * Presenter which connect DialogFragment and data
 */
public class DialogPresenter {
    private DialogFragment mView;

    /**
     * Initialize presenter by View Fragment
     * @param dialogFragment
     */
    public DialogPresenter(DialogFragment dialogFragment) {
        mView = dialogFragment;
    }

    /**
     * Detach view from presenter
     */
    public void detach() {
        mView = null;
    }

    /**
     * Getting list of messages
     * @return the messages
     */
    public ArrayList<String> getMessages() {
        return MessagesStore.getStore().getMessages();
    }
}
