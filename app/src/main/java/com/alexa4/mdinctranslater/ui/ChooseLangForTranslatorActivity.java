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
    private static final String TARGET_LANG = "TARGET_LANG";

    private Toolbar mToolbar;

    private int mTargetLang;

    private ChooseLangForTranslatorFragment mFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choose_lang_activity);

        //Getting target language
        mTargetLang = getIntent().getIntExtra(TARGET_LANG, 0);

        mToolbar = (Toolbar) findViewById(R.id.choose_lang_toolbar);

        if (mTargetLang == ChooseLangForTranslatorFragment.TARGET_FROM)
            mToolbar.setTitle(R.string.target_lang_from);
        else if (mTargetLang == ChooseLangForTranslatorFragment.TARGET_TO)
            mToolbar.setTitle(R.string.target_lang_to);

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
            mFragment = ChooseLangForTranslatorFragment.getInstance(mTargetLang);
    }


    /**
     * Initializing activity to select language for translator
     * @param context the context of app
     * @param target the target language which need change.
     *      To indicate target variables
     *      @see ChooseLangForTranslatorFragment target const
     * @return
     */
    public static Intent getInstance(Context context, int target) {
        Intent intent = new Intent(context, ChooseLangForTranslatorActivity.class);
        intent.putExtra(TARGET_LANG, target);
        return intent;
    }
}
