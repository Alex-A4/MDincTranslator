package com.alexa4.mdinctranslater.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.alexa4.mdinctranslater.R;
import com.alexa4.mdinctranslater.presenters.DialogPresenter;

import java.util.ArrayList;

//TODO: add saving of dialogs after rotating

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
        mDialogAdapter = new DialogAdapter();
    }
    
    /**
     * Clearing links
     */
    @Override
    public void onStop() {
        super.onStop();
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



    private class DialogAdapter extends RecyclerView.Adapter<mDialogViewHolder> {
        private static final int LEFT = 0;
        private static final int RIGHT = 1;

        @NonNull
        @Override
        public mDialogViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(getContext());

            mDialogViewHolder holder = null;

            if (viewType == LEFT) {
                holder = new DialogLeftView(inflater.inflate(R.layout.dialog_item_left, viewGroup,
                        false));
            } else {
                holder = new DialogRightView(inflater.inflate(R.layout.dialog_item_right, viewGroup,
                        false));
            }

            return holder;
        }

        @Override
        public void onBindViewHolder(@NonNull mDialogViewHolder holder, int position) {
            holder.setText(mMessages.get(mMessages.size() - position - 1));
        }

        /**
         * If the position is even then dialog will be right
         * else left
         * @param position
         * @return
         */
        @Override
        public int getItemViewType(int position) {
            if ((mMessages.size() - position) % 2 == 0)
                return RIGHT;
            else return LEFT;
        }

        @Override
        public int getItemCount() {
            return mMessages.size();
        }
    }


    /**
     * ViewHolder which response for left dialog item
     */
    class DialogLeftView extends mDialogViewHolder {
        private TextView mTextView;

        public DialogLeftView(@NonNull View itemView) {
            super(itemView);
            mTextView = (TextView) itemView.findViewById(R.id.card_text);
        }

        @Override
        public void setText(String text) {
            mTextView.setText(text);
        }
    }

    /**
     * ViewHolder which response for right dialog item
     */
    class DialogRightView extends mDialogViewHolder {
        private TextView mTextView;

        public DialogRightView(@NonNull View itemView) {
            super(itemView);
            mTextView = (TextView) itemView.findViewById(R.id.card_text);
        }

        @Override
        public void setText(String text) {
            mTextView.setText(text);
        }
    }
    /**
     * Parent class for dialog items
     */
    abstract class mDialogViewHolder extends RecyclerView.ViewHolder{
        public mDialogViewHolder(@NonNull View itemView) {
            super(itemView);
        }
        /**
         * Method to set text for dialog item
         */
        public abstract void setText(String text);
    }
}
