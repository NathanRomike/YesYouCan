package com.nathanromike.yes_you_can.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nathanromike.yes_you_can.R;
import com.nathanromike.yes_you_can.models.Guide;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GuideListAdapter extends RecyclerView.Adapter<GuideListAdapter.GuideViewHolder> {
    private ArrayList<Guide> mGuides = new ArrayList<>();
    private Context mContext;

    public GuideListAdapter(Context context, ArrayList<Guide> guides) {
        mContext = context;
        mGuides = guides;
    }

    @Override
    public GuideViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.guide_list_item, parent, false);
        GuideViewHolder viewHolder = new GuideViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(GuideViewHolder holder, int position) {
        holder.bindGuide(mGuides.get(position));
    }

    @Override
    public int getItemCount() {
        return mGuides.size();
    }

    public class GuideViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.coverImageView) ImageView mCoverImageView;
        @BindView(R.id.titleTextView) TextView mTitleTextView;
        @BindView(R.id.summaryTextView) TextView mSummaryTextView;
        @BindView(R.id.difficultyTextView) TextView mDifficultyTextView;

        private Context mContext;

        public GuideViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext = itemView.getContext();
        }

        public void bindGuide(Guide guide) {
            mTitleTextView.setText(guide.getTitle());
            mSummaryTextView.setText(guide.getSummary());
            mDifficultyTextView.setText("Difficulty: " + guide.getDifficulty());
        }
    }
}
