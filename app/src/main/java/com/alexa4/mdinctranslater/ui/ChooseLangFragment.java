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

import java.util.ArrayList;
import java.util.Arrays;

public class ChooseLangFragment extends Fragment {
    private ArrayList<String> mLangs = new ArrayList<>(Arrays.asList("Русский", "Английский",
            "Украинский", "Итальянский", "1", "2", "3",
            "4", "5", "6", "7", "8", "9", "10", "11", "12"));

    private RecyclerView mLangsLeftResView;
    private RecyclerView mLangsRightResView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.dialog_choose_lang_layout, container, false);

        final TextView leftSelectedText = (TextView) root.findViewById(R.id.left_selected_lang);
        final TextView rightSelectedText = (TextView) root.findViewById(R.id.right_selected_lang);

        mLangsLeftResView = (RecyclerView) root.findViewById(R.id.left_talker_langs);
        mLangsRightResView = (RecyclerView) root.findViewById(R.id.right_talker_langs);


        //Initializing right recyclerView
        mLangsRightResView.setLayoutManager(new LinearLayoutManager(getContext(),
                LinearLayoutManager.VERTICAL, false));
        mLangsRightResView.setAdapter(new ChooseDialogAdapter(mLangs, getContext(),
                new ChooseDialogItemClickListener() {
                    @Override
                    public void onClick(TextView view) {
                        rightSelectedText.setText(view.getText());
                    }
                }));



        //Initializing left recyclerView
        mLangsLeftResView.setLayoutManager(new LinearLayoutManager(getContext(),
                LinearLayoutManager.VERTICAL, false));
        mLangsLeftResView.setAdapter(new ChooseDialogAdapter(mLangs, getContext(),
                new ChooseDialogItemClickListener() {
                    @Override
                    public void onClick(TextView view) {
                        leftSelectedText.setText(view.getText());
                    }
                }));



        return root;
    }
}
