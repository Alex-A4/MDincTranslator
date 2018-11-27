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
import com.alexa4.mdinctranslater.data.LanguagesStore;

public class ChooseLangForTranslatorFragment extends Fragment {
    private static final String TARGET_VALUE = "TARGET_VALUE";
    public static final int TARGET_FROM = 0;
    public static final int TARGET_TO = 1;

    private LanguagesStore mStore;
    private TextView mSelectedText;
    private ChooseDialogAdapter mAdapter;


    //Variable which shows target language
    //It must be one of values: TARGET_FROM or TARGET_TO
    private int mTargetLang;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mStore = LanguagesStore.getStore();
        mTargetLang = getArguments().getInt(TARGET_VALUE, TARGET_FROM);
    }

    @Override
    public void onDestroy() {
        mStore = null;
        super.onDestroy();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.translator_choose_lang_layout, container, false);

        final TextView mSelectedText = root.findViewById(R.id.selected_lang_text);

        final RecyclerView mListOfLangs = root.findViewById(R.id.langs_recycler_view);
        mListOfLangs.setLayoutManager(new LinearLayoutManager(getContext(),
                LinearLayoutManager.VERTICAL, false));

        if (mAdapter == null)
            mAdapter = new ChooseDialogAdapter(mStore.getPairs(), getContext(),
                    new ChooseDialogItemClickListener() {
                        @Override
                        public void onClick(TextView view) {
                            String text = view.getText().toString();
                            mSelectedText.setText(text);

                            //Setting target lang in dependence of mTargetLang
                            if (mTargetLang == TARGET_FROM)
                                mStore.setLanguageFrom(mStore.getLangByName(text));
                            else if (mTargetLang == TARGET_TO)
                                mStore.setLanguageTo(mStore.getLangByName(text));
                        }
                    });


        mListOfLangs.setAdapter(mAdapter);

        return root;
    }


    /**
     * Initializing fragment by target language
     * @param target the value which shows target language
     *      TARGET_TO - language to translate
     *      TARGET_FROM - language from which translate
     * @return
     */
    public static ChooseLangForTranslatorFragment getInstance(int target) {
        ChooseLangForTranslatorFragment fragment = new ChooseLangForTranslatorFragment();

        Bundle args = new Bundle();
        args.putInt(TARGET_VALUE, target);
        fragment.setArguments(args);

        return fragment;
    }
}