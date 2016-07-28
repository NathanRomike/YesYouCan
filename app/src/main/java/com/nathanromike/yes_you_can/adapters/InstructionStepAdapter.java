package com.nathanromike.yes_you_can.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nathanromike.yes_you_can.R;
import com.nathanromike.yes_you_can.models.Instruction;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class InstructionStepAdapter extends RecyclerView.Adapter<InstructionStepAdapter.InstructionStepViewHolder> {
    private Instruction mInstruction;
    private Context mContext;

    public InstructionStepAdapter(Context context, Instruction instruction) {
        mContext = context;
        mInstruction = instruction;
    }

    @Override
    public InstructionStepViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.instruction_list_item, parent, false);
        return new InstructionStepViewHolder(view);
    }

    @Override
    public void onBindViewHolder(InstructionStepViewHolder holder, int position) {
        String imageUrl = mInstruction.getStepsimg().get(position);
        String stepText = mInstruction.getStepsText().get(position);
        holder.bindInstruction(imageUrl, stepText);
    }

    @Override
    public int getItemCount() {
        return mInstruction.getStepCount();
    }

    public class InstructionStepViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.coverImageView) ImageView mCoverImageView;
        @BindView(R.id.stepTextView) TextView mStepTextView;

        private Context mContext;

        public InstructionStepViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext = itemView.getContext();
        }

        public void bindInstruction(String imageUrl, String stepText) {
            mStepTextView.setText(Html.fromHtml(stepText));
            Picasso.with(mContext)
                .load(imageUrl)
                .into(mCoverImageView);
        }
    }
}
