package com.nathanromike.yes_you_can.ui;

import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.nathanromike.yes_you_can.R;
import com.nathanromike.yes_you_can.adapters.GuidePagerAdapter;
import com.nathanromike.yes_you_can.models.Guide;

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class InstructionDetailActivity extends AppCompatActivity {

    GuidePagerAdapter mGuidePagerAdapter;
    ArrayList<Guide> mGuides = new ArrayList<>();

    @BindView(R.id.viewPager) ViewPager mViewPager;
    @BindView(R.id.tool_bar) Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instruction_detail);
        supportPostponeEnterTransition();
        ButterKnife.bind(this);

        mGuides = Parcels.unwrap(getIntent().getParcelableExtra("guides"));
        int startingPosition = getIntent().getIntExtra("position", 0);

        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        ab.setDisplayShowTitleEnabled(false);

        mGuidePagerAdapter = new GuidePagerAdapter(getSupportFragmentManager(), mGuides);
        mViewPager.setAdapter(mGuidePagerAdapter);
        mViewPager.setCurrentItem(startingPosition);
    }
}
