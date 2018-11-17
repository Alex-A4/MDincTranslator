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

import java.util.ArrayList;

public class DialogFragment extends Fragment {
    private RecyclerView dialogView;
    private ArrayList<String> mMessages;
    private DialogAdapter mDialogAdapter;


    /**
     * Initializing messages list
     * @param savedInstanceState
     */
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mMessages = new ArrayList<>();
        mDialogAdapter = new DialogAdapter();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.dialog_fragment, container, false);

        dialogView = (RecyclerView) root.findViewById(R.id.dialog_recyclerview);
        //Set layout to scroll from bot to top
        dialogView.setLayoutManager(new LinearLayoutManager(getContext(),
                LinearLayoutManager.VERTICAL, true));
        dialogView.setAdapter(mDialogAdapter);

        final TextInputEditText messageText = (TextInputEditText) root.findViewById(R.id.dialog_input_text);

        ImageButton sendMessage = (ImageButton) root.findViewById(R.id.send_button);
        sendMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMessages.add(0, messageText.getText().toString());
                messageText.setText("");
                mDialogAdapter.notifyDataSetChanged();
            }
        });


        return root;
    }



    private class DialogAdapter extends RecyclerView.Adapter<DialogViewHolder> {
        private static final int LEFT = 0;
        private static final int RIGHT = 1;

        @NonNull
        @Override
        public DialogViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(getContext());

            DialogViewHolder holder = null;

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
        public void onBindViewHolder(@NonNull DialogViewHolder holder, int position) {
            holder.setText(mMessages.get(position));
        }

        /**
         * If the position is even then dialog will be right
         * else left
         *
         * @param position
         * @return
         */
        @Override
        public int getItemViewType(int position) {
            if (position % 2 == 0)
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
    class DialogLeftView extends DialogViewHolder {
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
    class DialogRightView extends DialogViewHolder {
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
    abstract class DialogViewHolder extends RecyclerView.ViewHolder{
        public DialogViewHolder(@NonNull View itemView) {
            super(itemView);
        }
        /**
         * Method to set text for dialog item
         */
        public abstract void setText(String text);
    }
}
