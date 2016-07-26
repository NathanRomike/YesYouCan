package com.nathanromike.yes_you_can.ui;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.nathanromike.yes_you_can.Constants;
import com.nathanromike.yes_you_can.R;
import com.nathanromike.yes_you_can.adapters.GuideListAdapter;
import com.nathanromike.yes_you_can.adapters.GuideListPagerAdapter;
import com.nathanromike.yes_you_can.services.iFixItService;
import com.nathanromike.yes_you_can.models.Guide;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class GuideListsActivity extends AppCompatActivity {
    @BindView(R.id.listViewpager) ViewPager viewPager;
    @BindView(R.id.sliding_tabs) TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide_list);
        ButterKnife.bind(this);

        viewPager.setAdapter(new GuideListPagerAdapter(getSupportFragmentManager(), GuideListsActivity.this));
        tabLayout.setupWithViewPager(viewPager);
    }
}
