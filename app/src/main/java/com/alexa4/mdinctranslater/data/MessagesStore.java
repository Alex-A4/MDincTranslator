package com.alexa4.mdinctranslater.data;


import java.util.ArrayList;

/**
 * Singleton class to store messages of dialogue
 * @author alexa4
 */
public class MessagesStore {
    //Singleton instance
    private static MessagesStore sStore;

    private ArrayList<String> mMessages;

    private MessagesStore() {
        mMessages = new ArrayList<>();
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
     * Add message to list
     * @param msg
     */
    public void addMessage(String msg) {
        mMessages.add(msg);
    }
}
