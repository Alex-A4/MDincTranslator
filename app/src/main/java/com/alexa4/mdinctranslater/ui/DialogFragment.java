package com.alexa4.mdinctranslater.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import com.alexa4.mdinctranslater.R;
import com.alexa4.mdinctranslater.adapters.DialogAdapter;
import com.alexa4.mdinctranslater.presenters.DialogPresenter;

import java.util.ArrayList;

/**
 * UI class providing dialog (system 1-1) between 2 people
 * @author alexa4
 */
public class DialogFragment extends Fragment {
    private RecyclerView mDialogView;
    private DialogAdapter mDialogAdapter;

    //List of messages
    private ArrayList<String> mMessages;

    private DialogPresenter mPresenter;

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
        View root = inflater.inflate(R.layout.dialog_fragment, container, false);

        mDialogView = (RecyclerView) root.findViewById(R.id.dialog_recyclerview);
        //Set layout to scroll from bot to top
        mDialogView.setLayoutManager(new LinearLayoutManager(getContext(),
                LinearLayoutManager.VERTICAL, true));
        mDialogView.setAdapter(mDialogAdapter);

        final TextInputEditText messageText = (TextInputEditText) root.findViewById(R.id.dialog_input_text);

        ImageButton sendMessage = (ImageButton) root.findViewById(R.id.send_button);
        sendMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = messageText.getText().toString().trim();
                if (!text.equals("")) {
                    mMessages.add(text);
                    messageText.setText("");
                    mDialogAdapter.notifyItemChanged(mMessages.size());
                }
            }
        });

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
                Toast.makeText(getContext(), "Dialog more selected!", Toast.LENGTH_SHORT).show();
                return true;

            default:return false;
        }
    }

    public String updateSutitle() {
        return mPresenter.getTranslateLanguagesSubtitle();
    }
}
