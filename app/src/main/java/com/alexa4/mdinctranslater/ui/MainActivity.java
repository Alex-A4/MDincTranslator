package com.alexa4.mdinctranslater.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.alexa4.mdinctranslater.R;
import com.alexa4.mdinctranslater.adapters.TranslatePagerAdapter;

import java.util.ArrayList;

/**
 * Main activity which contains viewPager with fragments
 */
public class MainActivity extends AppCompatActivity {
    private static final String translateTag = "TRANSLATE";
    private static final String dialogTag = "DIALOG";
    private int mCurrentHeight;
    private int mOldHeight;

    //The array which contains icons for tabs
    private int[] mIconsId = {R.drawable.ic_translator_icon, R.drawable.ic_dialog_icon};

    private View mRootView;
    private TranslatorFragment translateFragment;
    private DialogFragment dialogFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Listener to monitor screen changes (needs to check keyboard state)
        mRootView = findViewById(R.id.main_activity_root_layout);
        mRootView.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(View v, int left, int top, int right, int bottom,
                                       int oldLeft, int oldTop, int oldRight, int oldBottom) {
                mOldHeight = oldBottom;
                mCurrentHeight = bottom;
            }
        });

        initFragments();

        //Setting toolbar
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Initializing ViewPager
        final ViewPager fragmentsPager = (ViewPager) findViewById(R.id.view_pager);
        initViewPager(fragmentsPager);

        //Initializing TabLayout
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(fragmentsPager);
        tabLayout.addOnTabSelectedListener(new TabLayout.BaseOnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                fragmentsPager.setCurrentItem(tab.getPosition());
                updateSubtitle(tab.getPosition());
                Log.i("TAB_ID", "Tab " + tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
        //Setting icons for tabs
        for (int i = 0; i < mIconsId.length; i++)
            tabLayout.getTabAt(i).setIcon(mIconsId[i]);


    }

    public void updateSubtitle(int position) {
        String subtitle = null;
        if (position == 1)
            subtitle = dialogFragment.updateSubtitle();

        getSupportActionBar().setSubtitle(subtitle);
    }

    /**
     * Initializing fragments
     */
    private void initFragments() {
        FragmentManager manager = getSupportFragmentManager();
        translateFragment = (TranslatorFragment) manager.findFragmentByTag(translateTag);
        dialogFragment = (DialogFragment) manager.findFragmentByTag(dialogTag);

        if (translateFragment == null)
            translateFragment = new TranslatorFragment();

        if (dialogFragment == null)
            dialogFragment = new DialogFragment();
    }

    /**
     * Initializing ViewPager by fragments
     * @param fragmentsPager
     */
    private void initViewPager(ViewPager fragmentsPager) {
        final TranslatePagerAdapter adapter =
                new TranslatePagerAdapter(getSupportFragmentManager(), this);
        adapter.addFragment(translateFragment);
        adapter.addFragment(dialogFragment);
        fragmentsPager.setAdapter(adapter);

        //Set Toolbar title of first fragment
        getSupportActionBar().setTitle(adapter.getTitleByPosition(0));
        updateSubtitle(0);

        //Listener to change Toolbar title
        fragmentsPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {
            }

            @Override
            public void onPageSelected(int i) {
                getSupportActionBar().setTitle(adapter.getTitleByPosition(i));
                //If the keyboard is shown then hide it
                if (mCurrentHeight < mOldHeight) {
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {
            }
        });
    }

    /**
     * Override backPressed button to close activity when fragmentStack is empty
     */
    @Override
    public void onBackPressed() {
        FragmentManager manager = getSupportFragmentManager();
        manager.popBackStack();
        if (manager.getBackStackEntryCount() <= 1) {
            finish();
        }
    }

}
