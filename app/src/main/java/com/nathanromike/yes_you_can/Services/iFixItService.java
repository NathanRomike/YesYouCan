package com.nathanromike.yes_you_can.services;


import com.nathanromike.yes_you_can.Constants;
import com.nathanromike.yes_you_can.models.Guide;
import com.nathanromike.yes_you_can.models.Instruction;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class iFixItService {
    public void findGuides(String category, String guideId, Callback callback) {
        OkHttpClient client = new OkHttpClient.Builder().build();

        HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.IFIXIT_BASE_URL).newBuilder();

        //Pull list of guides by category
        if (guideId.equals(Constants.NO_DETAIL_TAG)) {
            urlBuilder.addPathSegment("categories");
            urlBuilder.addPathSegment(category);
        //Pulls details for a specific guide (Instruction model)
        } else {
            urlBuilder.addPathSegment("guides");
            urlBuilder.addPathSegment(guideId);
        }

        String url = urlBuilder.build().toString();

        Request request = new Request.Builder()
                .url(url)
                .build();

        Call call = client.newCall(request);
        call.enqueue(callback);
    }

    public ArrayList<Guide> processGuideResults(Response response) {
        ArrayList<Guide> guides = new ArrayList<>();
        try {
            String jsonData = response.body().string();
            if (response.isSuccessful()) {
                JSONObject fixItJSON = new JSONObject(jsonData);
                JSONArray guidesJSON = fixItJSON.getJSONArray("guides");
                for (int i = 0; i < guidesJSON.length(); i++) {
                    JSONObject guideJSON = guidesJSON.getJSONObject(i);
                    String guideId = guideJSON.getString("guideid");
                    String url = guideJSON.getString("url");
                    String title = guideJSON.getString("title");
                    String summary = guideJSON.getString("summary");
                    String difficulty = guideJSON.getString("difficulty");
                    double duration = guideJSON.getDouble("time_required_max");
                    JSONObject imagesJSON = guideJSON.getJSONObject("image");
                    String coverImg = imagesJSON.getString("medium");

                    Guide guide = new Guide(guideId, url, title, summary, difficulty, duration, coverImg);
                    guides.add(guide);
                }
            }
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        return guides;
    }

    public Instruction processInstructionResults(Response response) {
        Instruction instruction = new Instruction();
        ArrayList<String> stepsText = new ArrayList<>();
        ArrayList<String> stepsImg = new ArrayList<>();

        try {
            String jsonData = response.body().string();

            if (response.isSuccessful()) {
                JSONObject fixItJSON = new JSONObject(jsonData);
                String introduction = fixItJSON.getString("introduction_rendered");

                JSONArray stepsArray = fixItJSON.getJSONArray("steps");
                for (int i = 0; i < stepsArray.length(); i++) {

                    JSONObject stepObject = stepsArray.getJSONObject(i);
                    JSONArray linesArray = stepObject.getJSONArray("lines");
                    for (int j = 0; j < linesArray.length(); j++) {
                        JSONObject lineObject = linesArray.getJSONObject(j);
                        stepsText.add(lineObject.getString("text_rendered"));
                    }

                    JSONObject mediaObject = stepObject.getJSONObject("media");
                    JSONArray dataArray = mediaObject.getJSONArray("data");
                    for (int k = 0; k < dataArray.length(); k++) {
                        JSONObject dataObject = dataArray.getJSONObject(k);
                        stepsImg.add(dataObject.getString("standard"));
                    }
                }
                instruction = new Instruction(introduction, stepsText, stepsImg);
            }
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        return instruction;
    }
}
