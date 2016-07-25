package com.nathanromike.yes_you_can.models;

import org.parceler.Parcel;

@Parcel
public class Guide {
    private double mGuideId;
    private String mUrl;
    private String mTitle;
    private String mSummary;
    private String mDifficulty;
    private double mDuration;
    private String mCoverImg;

    public Guide() {}

    public Guide(double guideId, String url, String title, String summary, String difficulty, double duration, String coverImg) {
        this.mGuideId = guideId;
        this.mUrl = url;
        this.mTitle = title;
        this.mSummary = summary;
        this.mDifficulty = difficulty;
        this.mDuration = duration;
        this.mCoverImg = coverImg;
    }

    public double getGuideId() {
        return mGuideId;
    }

    public String getUrl() {
        return mUrl;
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

    public double getDuration() {
        return mDuration;
    }

    public String getCoverImg() {
        return mCoverImg;
    }
}
