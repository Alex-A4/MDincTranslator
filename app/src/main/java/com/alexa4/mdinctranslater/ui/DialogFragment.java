package com.alexa4.mdinctranslater.ui;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.Toast;

import com.alexa4.mdinctranslater.R;
import com.alexa4.mdinctranslater.adapters.DialogAdapter;
import com.alexa4.mdinctranslater.network.YandexTranslator;
import com.alexa4.mdinctranslater.presenters.DialogPresenter;

import java.util.ArrayList;

/**
 * UI class providing dialog (system 1-1) between 2 people
 * @author alexa4
 */
public class DialogFragment extends Fragment {
    private static final int CHOOSE_LANG_CODE = 10;
    private RecyclerView mDialogView;
    private DialogAdapter mDialogAdapter;
    private TextInputLayout mInputLayout;

    private MainActivity mActivity;

    //List of messages
    private ArrayList<String> mMessages;

    private DialogPresenter mPresenter;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (MainActivity) context;
    }

    /**
     * Initializing messages list
     * @param savedInstanceState
     */
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = new DialogPresenter(this);
        mMessages = mPresenter.getMessages();
        mDialogAdapter = new DialogAdapter(mMessages, getContext());
    }

    /**
     * Clearing links
     */
    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.detach();
        mMessages = null;
        mPresenter = null;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        //Initializing animation
        final Animation mClickAnimation = AnimationUtils.loadAnimation(getContext(),
                R.anim.click_animation);


        View root = inflater.inflate(R.layout.dialog_fragment, container, false);

        mDialogView = (RecyclerView) root.findViewById(R.id.dialog_recyclerview);
        //Set layout to scroll from bot to top
        mDialogView.setLayoutManager(new LinearLayoutManager(getContext(),
                LinearLayoutManager.VERTICAL, true));
        mDialogView.setAdapter(mDialogAdapter);

        mInputLayout = (TextInputLayout) root.findViewById(R.id.text_input_layout);

        final TextInputEditText mMessageText = (TextInputEditText) root.findViewById(R.id.dialog_input_text);

        final ImageButton sendMessage = (ImageButton) root.findViewById(R.id.send_button);
        sendMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(mClickAnimation);
                String text = mMessageText.getText().toString().trim();
                if (!text.equals(""))
                    if (isNetworkConnected()) {
                        //Translate the text
                        YandexTranslator.translateText(text, mPresenter.getLangsPairForTranslator(),
                                new YandexTranslator.TranslateCallback() {
                                    @Override
                                    public void sendTranslatedText(String text) {
                                        mMessages.add(text);
                                        mDialogAdapter.notifyItemChanged(mMessages.size());
                                        mDialogView.scrollToPosition(0);
                                    }
                                });

                        mMessageText.setText("");
                        mPresenter.swapTargetLanguages();
                        updateInputTextHint();
                    } else Toast.makeText(getContext(), R.string.check_internet,Toast.LENGTH_SHORT).show();
            }
        });

        updateInputTextHint();

        setHasOptionsMenu(true);

        return root;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.dialog_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.dialog_more:
                startActivityForResult(new Intent(getContext(), ChooseLangForDialogsActivity.class),
                        CHOOSE_LANG_CODE);
                return true;

            default:return false;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CHOOSE_LANG_CODE) {
            mActivity.updateSubtitle(1);
            updateInputTextHint();
        }

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

    public String updateSubtitle() {
        return mPresenter.getTranslateLanguagesSubtitle();
    }


    /**
     * Updating hint of input text according to current language
     */
    private void updateInputTextHint() {
        String tempLang = mPresenter.getNameOfLanguageFrom();

        String format = String.format(getString(R.string.write_message_on), tempLang);
        mInputLayout.setHint(format);
    }
}
