package com.alexa4.mdinctranslater.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.alexa4.mdinctranslater.R;
import com.alexa4.mdinctranslater.data.LanguageInfo;

import java.util.ArrayList;


/**
 * Adapter for recyclerView which contains list of TextViews
 * @author alexa4
 */
public class ChooseDialogAdapter extends RecyclerView.Adapter<ChooseDialogAdapter.ChooseViewHolder> {
    private ArrayList<LanguageInfo> mLanguagesLists;
    private Context mContext;
    private ChooseDialogItemClickListener mListener;
    private Animation mClickAnimation;

    public ChooseDialogAdapter(ArrayList<LanguageInfo> languages, Context context,
                               ChooseDialogItemClickListener listener) {
        mLanguagesLists = languages;
        mContext = context;
        mListener = listener;
        mClickAnimation = AnimationUtils.loadAnimation(context,
                R.anim.click_animation);
    }

    @NonNull
    @Override
    public ChooseViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View root = inflater.inflate(R.layout.choose_dialog_item, viewGroup, false);
        return new ChooseViewHolder(root);
    }

    @Override
    public void onBindViewHolder(@NonNull ChooseViewHolder holder, int position) {
        holder.mText.setText(mLanguagesLists.get(position).getLanguageName());
    }

    @Override
    public int getItemCount() {
        return mLanguagesLists.size();
    }

    class ChooseViewHolder extends RecyclerView.ViewHolder {
        TextView mText;
        ChooseViewHolder(@NonNull View itemView) {
            super(itemView);
            mText = (TextView) itemView.findViewById(R.id.choose_dialog_text_item);
            mText.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    v.startAnimation(mClickAnimation);
                    mListener.onClick(mText);
                }
            });
        }
    }
}