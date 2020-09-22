package com.example.newwidgets.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.List;

public class MyViewPagerAdapter extends FragmentStatePagerAdapter {

    private String[] mTitles;
    private List<Fragment> mFragments;

    public MyViewPagerAdapter(FragmentManager fm, String[] mTitles, List<Fragment> mFragments) {
        super(fm);
        this.mTitles = mTitles;
        this.mFragments = mFragments;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles[position];
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }
}
