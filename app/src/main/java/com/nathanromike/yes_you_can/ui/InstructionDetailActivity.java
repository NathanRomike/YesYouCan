package com.nathanromike.yes_you_can.ui;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.nathanromike.yes_you_can.R;
import com.nathanromike.yes_you_can.adapters.InstructionDetailPagerAdapter;
import com.nathanromike.yes_you_can.models.Guide;

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class InstructionDetailActivity extends AppCompatActivity {
    InstructionDetailPagerAdapter mGuidePagerAdapter;
    ArrayList<Guide> mGuides = new ArrayList<>();

    @BindView(R.id.viewPager) ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instruction_detail);
        ButterKnife.bind(this);

        mGuides = Parcels.unwrap(getIntent().getParcelableExtra("guides"));
        int startingPosition = getIntent().getIntExtra("position", 0);

        mGuidePagerAdapter = new InstructionDetailPagerAdapter(getSupportFragmentManager(), mGuides);
        mViewPager.setAdapter(mGuidePagerAdapter);
        mViewPager.setOffscreenPageLimit(3);
        mViewPager.setCurrentItem(startingPosition);
    }
}
