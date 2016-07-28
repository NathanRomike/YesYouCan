package com.nathanromike.yes_you_can.models;

import android.util.Log;

import org.parceler.Parcel;

import java.util.ArrayList;

@Parcel
public class Instruction {
    String mIntroduction;
    ArrayList<String> mStepsText;
    ArrayList<String> mStepsImg;

    public Instruction() {}

    public Instruction(String introduction, ArrayList<String> stepsText, ArrayList<String> stepsImg) {
        this.mIntroduction = introduction;
        this.mStepsText = stepsText;
        this.mStepsImg = stepsImg;
    }

    public String getIntroduction() {
        // API includes links like: [product|IF145-035|multimeter]
        // Regex to pull between first [product| to the second pipe = /\[(.*)\|/
        return mIntroduction;
    }

    public ArrayList<String> getStepsText() {
        return mStepsText;
    }

    public ArrayList<String> getStepsimg() {
        return mStepsImg;
    }

    public int getStepCount() {
        if (mStepsImg.size() < mStepsText.size()) {
            return mStepsImg.size();
        } else {
            return mStepsText.size();
        }
    }
}
