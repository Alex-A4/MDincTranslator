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

import com.alexa4.mdinctranslater.R;

public class DialogFragment extends Fragment {
    private RecyclerView dialogView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.dialog_fragment, container, false);

        dialogView = (RecyclerView) root.findViewById(R.id.dialog_recyclerview);
        dialogView.setLayoutManager(new LinearLayoutManager(getContext()));

        return root;
    }



    private class DialogAdapter extends RecyclerView.Adapter<DialogAdapter.DialogViewHolder> {
        @NonNull
        @Override
        public DialogViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            return null;
        }

        @Override
        public void onBindViewHolder(@NonNull DialogViewHolder dialogViewHolder, int i) {

        }

        @Override
        public int getItemCount() {
            return 0;
        }

        class DialogViewHolder extends RecyclerView.ViewHolder {

            public DialogViewHolder(@NonNull View itemView) {
                super(itemView);
            }
        }
    }
}
