package com.alexa4.mdinctranslater.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatTextView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alexa4.mdinctranslater.R;

public class TranslateDialog extends Fragment {
    private TextInputEditText mInputText;
    private AppCompatTextView mTextView;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.translate_fragment, container, false);

        mTextView = (AppCompatTextView) root.findViewById(R.id.translated_text);

        mInputText = (TextInputEditText) root.findViewById(R.id.text_to_translate);
        //Listener to translate text after user stop writing
        mInputText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                String text = mInputText.getText().toString().trim();

                if (!text.equals("")) {
                    mTextView.setText(text);
                }
            }
        });

        return root;
    }
}
