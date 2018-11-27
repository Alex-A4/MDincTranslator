package com.alexa4.mdinctranslater.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alexa4.mdinctranslater.R;
import com.alexa4.mdinctranslater.adapters.ChooseDialogAdapter;
import com.alexa4.mdinctranslater.adapters.ChooseDialogItemClickListener;
import com.alexa4.mdinctranslater.data.LanguageInfo;
import com.alexa4.mdinctranslater.data.MessagesStore;

import java.util.ArrayList;

public class ChooseLangForDialogsFragment extends Fragment {
    private ArrayList<LanguageInfo> mLangs;

    private RecyclerView mLangsLeftResView;
    private RecyclerView mLangsRightResView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mLangs = MessagesStore.getStore().getPairs();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.dialog_choose_lang_layout, container, false);

        final TextView leftSelectedText = (TextView) root.findViewById(R.id.left_selected_lang);
        leftSelectedText.setText(MessagesStore.getStore().getLanguageFrom().getLanguageName());

        final TextView rightSelectedText = (TextView) root.findViewById(R.id.right_selected_lang);
        rightSelectedText.setText(MessagesStore.getStore().getLanguageTo().getLanguageName());

        mLangsLeftResView = (RecyclerView) root.findViewById(R.id.left_talker_langs);
        mLangsRightResView = (RecyclerView) root.findViewById(R.id.right_talker_langs);



        //Initializing left recyclerView
        mLangsLeftResView.setLayoutManager(new LinearLayoutManager(getContext(),
                LinearLayoutManager.VERTICAL, false));
        mLangsLeftResView.setAdapter(new ChooseDialogAdapter(mLangs, getContext(),
                new ChooseDialogItemClickListener() {
                    @Override
                    public void onClick(TextView view) {
                        //Setting langFrom to store
                        String name = view.getText().toString();
                        MessagesStore store = MessagesStore.getStore();

                        store.setLanguageFrom(store.getLangByName(name));
                        leftSelectedText.setText(name);
                    }
                }));


        //Initializing right recyclerView
        mLangsRightResView.setLayoutManager(new LinearLayoutManager(getContext(),
                LinearLayoutManager.VERTICAL, false));
        mLangsRightResView.setAdapter(new ChooseDialogAdapter(mLangs, getContext(),
                new ChooseDialogItemClickListener() {
                    @Override
                    public void onClick(TextView view) {
                        //Setting langTo to store
                        String name = view.getText().toString();
                        MessagesStore store = MessagesStore.getStore();

                        store.setLanguageTo(store.getLangByName(name));
                        rightSelectedText.setText(name);
                    }
                }));


        return root;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mLangs = null;
    }
}
