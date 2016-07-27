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
    @BindView(R.id.listViewpager) ViewPager viewPager;
    @BindView(R.id.sliding_tabs) TabLayout tabLayout;
    @BindView(R.id.tool_bar) Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_lists);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setDisplayShowTitleEnabled(false);
            ab.setDisplayHomeAsUpEnabled(true);
        }

        viewPager.setAdapter(new TabLayoutPagerAdapter(getSupportFragmentManager(), CardListsActivity.this));
        viewPager.setOffscreenPageLimit(3);
        tabLayout.setupWithViewPager(viewPager);
    }
}
