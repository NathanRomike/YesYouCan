package com.nathanromike.yes_you_can.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nathanromike.yes_you_can.R;
import com.nathanromike.yes_you_can.models.Guide;
import com.nathanromike.yes_you_can.ui.GuideDetailActivity;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

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

    public class GuideViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.coverImageView) ImageView mCoverImageView;
        @BindView(R.id.titleTextView) TextView mTitleTextView;
        @BindView(R.id.summaryTextView) TextView mSummaryTextView;
        @BindView(R.id.difficultyTextView) TextView mDifficultyTextView;

        private Context mContext;

        public GuideViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext = itemView.getContext();
            itemView.setOnClickListener(this);
        }

        public void bindGuide(Guide guide) {
            Picasso.with(mContext)
                    .load(guide.getCoverImg())
                    .into(mCoverImageView);
            mTitleTextView.setText(guide.getTitle());
            mSummaryTextView.setText(guide.getSummary());
            mDifficultyTextView.setText("Difficulty: " + guide.getDifficulty());
        }

        @Override
        public void onClick(View view) {
            int itemPosition = getLayoutPosition();
            Intent intent = new Intent(mContext, GuideDetailActivity.class);
            intent.putExtra("position", itemPosition);
            intent.putExtra("guides", Parcels.wrap(mGuides));
            mContext.startActivity(intent);
        }
    }
}
