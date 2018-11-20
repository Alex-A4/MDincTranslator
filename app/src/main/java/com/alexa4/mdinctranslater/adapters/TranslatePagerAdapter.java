package com.alexa4.mdinctranslater.adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.alexa4.mdinctranslater.R;

import java.util.ArrayList;


/**
 * Adapter for ViewPager on MainActivity
 * @author alexa4
 */
public class TranslatePagerAdapter extends FragmentPagerAdapter {
    //List of main fragments
    private ArrayList<Fragment> mFragments;
    private Context mContext;

    public TranslatePagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        mFragments = new ArrayList<>();
        mContext = context;
    }

    @Override
    public Fragment getItem(int i) {
        return mFragments.get(i);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }


    /**
     * Add fragment to list
     * @param fragment the added fragment
     */
    public void addFragment(Fragment fragment) {
        mFragments.add(fragment);
    }


    public String getTitleByPosition(int position) {
        switch (position) {
            case 0: return mContext.getString(R.string.translator_tab);
            case 1: return mContext.getString(R.string.dialogs_tab);
            default: return "Error";
        }
    }

}