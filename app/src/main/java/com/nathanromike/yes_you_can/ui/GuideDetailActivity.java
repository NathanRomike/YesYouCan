package com.nathanromike.yes_you_can.ui;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.nathanromike.yes_you_can.R;
import com.nathanromike.yes_you_can.adapters.GuidePagerAdapter;
import com.nathanromike.yes_you_can.models.Guide;

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GuideDetailActivity extends AppCompatActivity {
    @BindView(R.id.viewPager) ViewPager mViewPager;
    private GuidePagerAdapter guidePagerAdapter;
    ArrayList<Guide> mGuides = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide_detail);
        ButterKnife.bind(this);

        mGuides = Parcels.unwrap(getIntent().getParcelableExtra("guides"));
        int startingPosition = getIntent().getIntExtra("position", 0);

        guidePagerAdapter = new GuidePagerAdapter(getSupportFragmentManager(), mGuides);
        mViewPager.setAdapter(guidePagerAdapter);
        mViewPager.setCurrentItem(startingPosition);
    }
}
