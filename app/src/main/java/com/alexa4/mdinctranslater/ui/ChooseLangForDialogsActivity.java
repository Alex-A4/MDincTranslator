package com.alexa4.mdinctranslater.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.alexa4.mdinctranslater.R;

public class ChooseLangForDialogsActivity extends AppCompatActivity {
    private static final String LANG_FRAGMENT_TAG = "LANG_FRAGMENT";

    private Toolbar mToolbar;
    private ChooseLangForDialogsFragment mLangFragment;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choose_lang_activity);

        mToolbar = findViewById(R.id.choose_lang_toolbar);
        mToolbar.setTitle(R.string.choose_dialog_toolbar_text);
        setSupportActionBar(mToolbar);

        initFragment();

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.choose_dialog_container, mLangFragment)
                .commit();
    }

    private void initFragment() {
        FragmentManager manager = getSupportFragmentManager();
        mLangFragment = (ChooseLangForDialogsFragment) manager.findFragmentByTag(LANG_FRAGMENT_TAG);

        if (mLangFragment == null)
            mLangFragment = new ChooseLangForDialogsFragment();
    }
}
