package com.alexa4.mdinctranslater.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.alexa4.mdinctranslater.R;

public class ChooseLangForTranslatorActivity extends AppCompatActivity {
    private static final String CHOOSE_LANG_TAG = "CHOOSE_LANG";
    private Toolbar mToolbar;

    private ChooseLangForTranslatorFragment mFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choose_lang_activity);



        initFragment();

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.choose_dialog_container, mFragment)
                .commit();
    }



    /**
     * Initializing fragment to choose languages
     */
    private void initFragment() {
        FragmentManager manager = getSupportFragmentManager();

        mFragment = (ChooseLangForTranslatorFragment) manager.findFragmentByTag(CHOOSE_LANG_TAG);

        if (mFragment == null)
            mFragment = new ChooseLangForTranslatorFragment();
    }


    /**
     * TODO: add logic to instance target language
     * @param context
     * @param target
     * @return
     */
    public static Intent getInstance(Context context, String target) {
        return new Intent(context, ChooseLangForTranslatorActivity.class);
    }
}
