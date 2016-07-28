package com.nathanromike.yes_you_can.ui;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.nathanromike.yes_you_can.R;
import com.nathanromike.yes_you_can.adapters.TabLayoutPagerAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CardListsActivity extends AppCompatActivity {
    @BindView(R.id.listViewpager) ViewPager mViewPager;
    @BindView(R.id.sliding_tabs) TabLayout mTabLayout;
    @BindView(R.id.tool_bar) Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_lists);
        ButterKnife.bind(this);

        setSupportActionBar(mToolbar);
        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setDisplayShowTitleEnabled(false);
            ab.setDisplayHomeAsUpEnabled(true);
        }

        mViewPager.setAdapter(new TabLayoutPagerAdapter(getSupportFragmentManager(), CardListsActivity.this));
        mViewPager.setOffscreenPageLimit(3);
        mTabLayout.setupWithViewPager(mViewPager);
    }
}
