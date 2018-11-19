package com.alexa4.mdinctranslater.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatImageButton;
import android.support.v7.widget.AppCompatTextView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alexa4.mdinctranslater.R;

public class TranslatorFragment extends Fragment {
    private TextInputEditText mInputText;
    private AppCompatTextView mTranslatedText;
    private AppCompatImageButton mTranslateButton;
    private AppCompatImageButton mClearAllButton;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.translate_fragment, container, false);

        mInputText = (TextInputEditText) root.findViewById(R.id.text_to_translate);

        mTranslatedText = (AppCompatTextView) root.findViewById(R.id.translated_text);

        mTranslateButton = (AppCompatImageButton) root.findViewById(R.id.translate_button);
        mTranslateButton.setOnClickListener(new View.OnClickListener() {
            //TODO: add logic to translate text
            @Override
            public void onClick(View v) {
                String text = mInputText.getText().toString().trim();

                if (!text.equals("")) {
                    mTranslatedText.setText(text);
                }
            }
        });

        mClearAllButton = (AppCompatImageButton) root.findViewById(R.id.clear_all_button);
        mClearAllButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mInputText.setText("");
                mTranslatedText.setText("");
            }
        });


        return root;
    }
}
