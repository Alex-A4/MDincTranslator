package com.alexa4.mdinctranslater.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.alexa4.mdinctranslater.R;

import java.util.ArrayList;

/**
 * Main activity which contains viewPager with fragments
 */
public class MainActivity extends AppCompatActivity {
    private static final String translateTag = "TRANSLATE";
    private static final String dialogTag = "DIALOG";

    private TranslatorFragment translateFragment;
    private DialogFragment dialogFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initFragments();

        //Setting toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
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
                Log.i("TAB_ID", "Tab " + tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });


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
        final TranslatePagerAdapter adapter = new TranslatePagerAdapter(getSupportFragmentManager());
        adapter.addFragment(translateFragment);
        adapter.addFragment(dialogFragment);
        fragmentsPager.setAdapter(adapter);
        getSupportActionBar().setTitle(adapter.getPageTitle(0));
        fragmentsPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {
            }

            @Override
            public void onPageSelected(int i) {
                getSupportActionBar().setTitle(adapter.getPageTitle(i));
            }

            @Override
            public void onPageScrollStateChanged(int i) {
            }
        });
    }


    class TranslatePagerAdapter extends FragmentPagerAdapter {
        private ArrayList<Fragment> mFragments;

        public TranslatePagerAdapter(FragmentManager fm) {
            super(fm);
            mFragments = new ArrayList<>();
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


        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0: return getString(R.string.translator_tab);
                case 1: return getString(R.string.dialogs_tab);
                default: return "Error";
            }
        }
    }

    @Override
    public void onBackPressed() {
        FragmentManager manager = getSupportFragmentManager();
        manager.popBackStack();
        if (manager.getBackStackEntryCount() <= 1) {
            finish();
        }
    }

}
