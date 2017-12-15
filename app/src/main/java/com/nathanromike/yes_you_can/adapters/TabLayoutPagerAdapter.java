package com.nathanromike.yes_you_can.adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.nathanromike.yes_you_can.ui.CardListFragment;

public class TabLayoutPagerAdapter extends FragmentPagerAdapter {
    final int PAGE_COUNT = 4;
    private String tabTitles[] = new String[] {"Tech", "Sewing", "Software", "Soldering"};
    private Context mContext;

    public TabLayoutPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.mContext = context;
    }


    @Override
    public Fragment getItem(int position) {
        return CardListFragment.newInstance(position + 1);
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }
}
