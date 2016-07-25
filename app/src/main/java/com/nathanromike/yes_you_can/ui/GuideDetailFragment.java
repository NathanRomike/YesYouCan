package com.nathanromike.yes_you_can.ui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nathanromike.yes_you_can.R;
import com.nathanromike.yes_you_can.models.Guide;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GuideDetailFragment extends Fragment {
    @BindView(R.id.titleTextView) TextView mTitleTextView;
    @BindView(R.id.coverImageView) ImageView mCoverImageView;
    @BindView(R.id.summaryTextView) TextView mSummaryTextView;

    public GuideDetailFragment() {
        // Required empty public constructor
    }

    private Guide mGuide;

    public static GuideDetailFragment newInstance(Guide guide) {
        GuideDetailFragment guideDetailFragment = new GuideDetailFragment();
        Bundle args = new Bundle();
        args.putParcelable("guide", Parcels.wrap(guide));
        guideDetailFragment.setArguments(args);
        return guideDetailFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mGuide = Parcels.unwrap(getArguments().getParcelable("guide"));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_guide_detail, container, false);
        ButterKnife.bind(this, view);
        mTitleTextView.setText(mGuide.getTitle());
        Picasso.with(view.getContext())
                .load(mGuide.getCoverImg())
                .into(mCoverImageView);
        mSummaryTextView.setText(mGuide.getSummary());

        return view;
    }

}
