package com.nathanromike.yes_you_can.ui;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.nathanromike.yes_you_can.R;
import com.nathanromike.yes_you_can.adapters.GuideListPagerAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GuideListsActivity extends AppCompatActivity {
    @BindView(R.id.listViewpager) ViewPager viewPager;
    @BindView(R.id.sliding_tabs) TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide_list);
        ButterKnife.bind(this);

        viewPager.setAdapter(new GuideListPagerAdapter(getSupportFragmentManager(), GuideListsActivity.this));
        viewPager.setOffscreenPageLimit(3);
        tabLayout.setupWithViewPager(viewPager);
    }
}
