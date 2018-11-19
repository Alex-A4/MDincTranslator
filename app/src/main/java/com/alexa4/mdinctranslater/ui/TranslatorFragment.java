package com.alexa4.mdinctranslater.ui;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
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
import com.alexa4.mdinctranslater.network.YandexTranslator;

public class TranslatorFragment extends Fragment {
    private TextInputEditText mInputText;
    private AppCompatTextView mTranslatedText;
    private AppCompatImageButton mTranslateButton;
    private AppCompatImageButton mClearAllButton;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.translate_fragment, container, false);

        //TODO: add logic to select languages
        //Bottom text fields which show languages for translating
        final TextView textFrom = (TextView) root.findViewById(R.id.from_language_text);
        final TextView textTo = (TextView)  root.findViewById(R.id.to_language_text);

        mInputText = (TextInputEditText) root.findViewById(R.id.text_to_translate);

        mTranslatedText = (AppCompatTextView) root.findViewById(R.id.translated_text);

        mTranslateButton = (AppCompatImageButton) root.findViewById(R.id.translate_button);
        mTranslateButton.setOnClickListener(new View.OnClickListener() {
            //TODO: add logic to translate text
            @Override
            public void onClick(View v) {
                String text = mInputText.getText().toString().trim();

                if (isNetworkConnected()) {
                    if (!text.equals(""))
                        YandexTranslator.translateText(text, "ru-en",
                                new YandexTranslator.TranslateCallback() {
                                    @Override
                                    public void sendTranslatedText(String text) {
                                        mTranslatedText.setText(text);
                                    }
                                });
                } else Toast.makeText(getContext(), R.string.check_internet, Toast.LENGTH_SHORT).show();

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

        //TODO: add logic to swap languages
        final ImageButton mSwapButton = (ImageButton) root.findViewById(R.id.swap_languages_button);
        mSwapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tempText = textFrom.getText().toString();
                textFrom.setText(textTo.getText());
                textTo.setText(tempText);
            }
        });

        setHasOptionsMenu(true);

        return root;
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
}
