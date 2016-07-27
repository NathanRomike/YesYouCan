package com.nathanromike.yes_you_can.ui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.nathanromike.yes_you_can.Constants;
import com.nathanromike.yes_you_can.R;
import com.nathanromike.yes_you_can.models.Guide;
import com.nathanromike.yes_you_can.models.Instruction;
import com.nathanromike.yes_you_can.services.iFixItService;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class InstructionDetailFragment extends Fragment {
    @BindView(R.id.titleTextView) TextView mTitleTextView;
    @BindView(R.id.coverImageView) ImageView mCoverImageView;
    @BindView(R.id.introTextView) TextView mIntroTextView;
    @BindView(R.id.listView) ListView mListView;

    public InstructionDetailFragment() {}

    private Guide mGuide;
    private Instruction mInstruction;

    public static InstructionDetailFragment newInstance(Guide guide) {
        InstructionDetailFragment guideDetailFragment = new InstructionDetailFragment();
        Bundle args = new Bundle();
        args.putParcelable("guide", Parcels.wrap(guide));
        guideDetailFragment.setArguments(args);
        return guideDetailFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mGuide = Parcels.unwrap(getArguments().getParcelable("guide"));
        makeAPICallForDetails();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_instruction_detail, container, false);
        ButterKnife.bind(this, view);
        mTitleTextView.setText(mGuide.getTitle());
        Picasso.with(view.getContext())
                .load(mGuide.getCoverImg())
                .into(mCoverImageView);

        getActivity().startPostponedEnterTransition();
        return view;
    }

    public void makeAPICallForDetails() {
        String guideId = mGuide.getGuideId();
        final iFixItService fixItService = new iFixItService();
        fixItService.findGuides(Constants.NO_CATEGORY_TAG, guideId, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                mInstruction = fixItService.processInstructionResults(response);

                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mIntroTextView.setText(mInstruction.getIntroduction());

                        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, mInstruction.getSteps());
                        mListView.setAdapter(adapter);
                    }
                });

            }
        });
    }

}
