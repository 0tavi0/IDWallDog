package com.jonasvieira.iddog.Presentations.Feed.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.jonasvieira.iddog.Presentations.Feed.FeedActivity;
import com.jonasvieira.iddog.Presentations.Feed.FeedFragment;

public class TabAdapter extends FragmentPagerAdapter {

    private int mTabCount;

    public TabAdapter(FragmentManager fm, int tabCount) {
        super(fm);
        this.mTabCount = tabCount;
    }

    @Override
    public Fragment getItem(int position) {
        return new FeedFragment();
    }

    @Override
    public int getCount() {
        return mTabCount;
    }
}
