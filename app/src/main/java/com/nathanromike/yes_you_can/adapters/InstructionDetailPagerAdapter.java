package com.nathanromike.yes_you_can.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.nathanromike.yes_you_can.models.Guide;
import com.nathanromike.yes_you_can.ui.InstructionDetailFragment;

import java.util.ArrayList;

public class InstructionDetailPagerAdapter extends FragmentPagerAdapter {
    private ArrayList<Guide> mGuides;

    public InstructionDetailPagerAdapter(FragmentManager fm, ArrayList<Guide> guides) {
        super(fm);
        mGuides = guides;
    }


    @Override
    public Fragment getItem(int position) {
        return InstructionDetailFragment.newInstance(mGuides.get(position));
    }

    @Override
    public int getCount() {
        return mGuides.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mGuides.get(position).getTitle();
    }
}
