package com.nathanromike.yes_you_can.models;

import org.parceler.Parcel;

import java.util.ArrayList;

@Parcel
public class Instruction {
    String mIntroduction;
    ArrayList<String> mSteps;
    String mTimeRequired;
    ArrayList<String> mTools;
    String mAuthorName;

    public Instruction() {}

    public Instruction(String introduction, ArrayList<String> steps, String timeRequired, ArrayList<String> tools, String authorName) {
        this.mIntroduction = introduction;
        this.mSteps = steps;
        this.mTimeRequired = timeRequired;
        this.mTools = tools;
        this.mAuthorName = authorName;
    }

    public String getIntroduction() {
        return mIntroduction;
    }

    public ArrayList<String> getSteps() {
        return mSteps;
    }

    public String getTimeRequired() {
        return mTimeRequired;
    }

    public ArrayList<String> getTools() {
        return mTools;
    }

    public String getAuthorName() {
        return mAuthorName;
    }

    public void setmIntroduction(String mIntroduction) {
        this.mIntroduction = mIntroduction;
    }

    public void setmSteps(ArrayList<String> mSteps) {
        this.mSteps = mSteps;
    }

    public void setmTimeRequired(String mTimeRequired) {
        this.mTimeRequired = mTimeRequired;
    }

    public void setmTools(ArrayList<String> mTools) {
        this.mTools = mTools;
    }

    public void setmAuthorName(String mAuthorName) {
        this.mAuthorName = mAuthorName;
    }
}
