package com.nathanromike.yes_you_can.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nathanromike.yes_you_can.Constants;
import com.nathanromike.yes_you_can.R;
import com.nathanromike.yes_you_can.adapters.MainListRecyclerAdapter;
import com.nathanromike.yes_you_can.models.Guide;
import com.nathanromike.yes_you_can.services.iFixItService;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class CardListFragment extends Fragment {
    public static final String ARG_PAGE = "ARG_PAGE";
    public int mPage;
    public String mCategory;

    @BindView(R.id.recyclerView) RecyclerView mRecyclerView;
    private MainListRecyclerAdapter mAdapter;

    public ArrayList<Guide> mGuides = new ArrayList<>();

    public CardListFragment() {}

    public static CardListFragment newInstance(int page) {
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        CardListFragment fragment = new CardListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPage = getArguments().getInt(ARG_PAGE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_card_list, container, false);
        ButterKnife.bind(this, view);
        getGuides(mPage);
        return view;
    }

    private void getGuides(int currentPage) {
        switch (currentPage) {
            case 1:
                mCategory = Constants.ELECTRONIC;
                break;
            case 2:
                mCategory = Constants.SEWING;
                break;
            case 3:
                mCategory = Constants.SOFTWARE;
                break;
            case 4:
                mCategory = Constants.SOLDERING;
                break;
        }

        final iFixItService fixItService = new iFixItService();
        fixItService.findGuides(mCategory, Constants.NO_DETAIL_TAG, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    mGuides = fixItService.processGuideResults(response);
                    mAdapter = new MainListRecyclerAdapter(getActivity(), mGuides);
                    mRecyclerView.setAdapter(mAdapter);
                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
                    mRecyclerView.setLayoutManager(layoutManager);
                    mRecyclerView.setHasFixedSize(true);
                }
            }
        });
    }

}
