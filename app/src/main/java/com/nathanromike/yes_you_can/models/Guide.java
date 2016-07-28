package com.nathanromike.yes_you_can.models;

import org.parceler.Parcel;

@Parcel
public class Guide {
    private String mGuideId;
    private String mTitle;
    private String mSummary;
    private String mDifficulty;
    private String mCoverImg;

    public Guide() {}

    public Guide(String guideId, String title, String summary, String difficulty, String coverImg) {
        this.mGuideId = guideId;
        this.mTitle = title;
        this.mSummary = summary;
        this.mDifficulty = difficulty;
        this.mCoverImg = coverImg;
    }

    public String getGuideId() {
        return mGuideId;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getSummary() {
        return mSummary;
    }

    public String getDifficulty() {
        return mDifficulty;
    }

    public String getCoverImg() {
        return mCoverImg;
    }
}
