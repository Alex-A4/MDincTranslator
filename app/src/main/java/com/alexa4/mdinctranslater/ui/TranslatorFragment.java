package com.alexa4.mdinctranslater.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.button.MaterialButton;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatImageButton;
import android.support.v7.widget.AppCompatTextView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.alexa4.mdinctranslater.R;
import com.alexa4.mdinctranslater.presenters.TranslatorPresenter;

public class TranslatorFragment extends Fragment {
    private TextInputEditText mInputText;
    private AppCompatTextView mTranslatedText;
    private MaterialButton mTranslateButton;
    private AppCompatImageButton mClearAllButton;
    private TextView mTextTo;
    private TextView mTextFrom;

    private TranslatorPresenter mPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = new TranslatorPresenter(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.detach();
        mPresenter = null;
    }

    @SuppressLint("RestrictedApi")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.translate_fragment, container, false);

        //Bottom text fields which show languages for translating
        mTextFrom = (TextView) root.findViewById(R.id.from_language_text);
        mTextFrom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Starting activity to choose language
                Intent intent = ChooseLangForTranslatorActivity.getInstance(getContext(),
                        ChooseLangForTranslatorFragment.TARGET_FROM);
                startActivityForResult(intent, 0);
            }
        });

        mTextTo = (TextView)  root.findViewById(R.id.to_language_text);
        mTextTo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Starting activity to choose language
                Intent intent = ChooseLangForTranslatorActivity.getInstance(getContext(),
                        ChooseLangForTranslatorFragment.TARGET_TO);
                startActivityForResult(intent, 0);
            }
        });


        mInputText = (TextInputEditText) root.findViewById(R.id.text_to_translate);
        //Setting transparent color of underline
        ColorStateList colorStateList = ColorStateList.valueOf(Color.TRANSPARENT);
        mInputText.setSupportBackgroundTintList(colorStateList);

        mTranslatedText = (AppCompatTextView) root.findViewById(R.id.translated_text);

        mTranslateButton = (MaterialButton) root.findViewById(R.id.translate_button);
        mTranslateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = mInputText.getText().toString().trim();

                if (!text.equals(""))
                    translateText(text);
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

        //TODO: add logic to swap text into InputText and Translated text and translate the first
        final ImageButton mSwapButton = (ImageButton) root.findViewById(R.id.swap_languages_button);
        mSwapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.swapTargetLanguages();
                updateLanguagesLayout();

                String text = mTranslatedText.getText().toString();
                mInputText.setText(text);
                mTranslatedText.setText("");
                translateText(text);
            }
        });

        updateLanguagesLayout();
        setHasOptionsMenu(true);

        return root;
    }


    /**
     * Translating text thanks to Yandex.Translate
     * @param text  the text which need translate
     */
    private void translateText(String text) {
        if (isNetworkConnected())
            mPresenter.getTranslatedText(text);
        else Toast.makeText(getContext(), R.string.check_internet, Toast.LENGTH_SHORT).show();
    }

    /**
     * Update the current selected languages
     */
    private void updateLanguagesLayout() {
        mTextFrom.setText(mPresenter.getLanguageNameOfFromText());
        mTextTo.setText(mPresenter.getLanguageNameOfToText());
    }


    /**
     * Check network connection
     * @return is the internet enabled
     */
    private boolean isNetworkConnected(){
        ConnectivityManager connectivityManager = (ConnectivityManager) getActivity()
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.translator_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            //TODO: Add logic to start settings fragment
            case R.id.translator_settings:
                Toast.makeText(getContext(), "Settings clicked", Toast.LENGTH_SHORT).show();
                return true;

            default: return false;
        }
    }


    /**
     * Updating the languages layout when user returns from activity to choose langs
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 0)
            updateLanguagesLayout();
    }

    /**
     * Set the translated text to special TextView
     * @param translatedText the translated text
     */
    public void setTranslatedText(String translatedText) {
        mTranslatedText.setText(translatedText);
    }
}
