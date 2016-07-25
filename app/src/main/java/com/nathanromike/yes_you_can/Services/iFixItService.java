package com.nathanromike.yes_you_can.Services;

import android.util.Log;

import com.nathanromike.yes_you_can.Constants;
import com.nathanromike.yes_you_can.models.Guide;

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
    public static void findGuides(String category, Callback callback) {
        OkHttpClient client = new OkHttpClient.Builder().build();

        HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.IFIXIT_BASE_URL).newBuilder();
        urlBuilder.addPathSegment("categories");
        urlBuilder.addPathSegment(category);
        String url = urlBuilder.build().toString();

        Request request = new Request.Builder()
                .url(url)
                .build();

        Call call = client.newCall(request);
        call.enqueue(callback);
    }

    public ArrayList<Guide> processResults(Response response) {
        ArrayList<Guide> guides = new ArrayList<>();
        try {
            String jsonData = response.body().string();
            if (response.isSuccessful()) {
                JSONObject fixItJSON = new JSONObject(jsonData);
                JSONArray guidesJSON = fixItJSON.getJSONArray("guides");
                for (int i = 0; i < guidesJSON.length(); i++) {
                    JSONObject guideJSON = guidesJSON.getJSONObject(i);
                    double guideId = guideJSON.getDouble("guideid");
                    String url = guideJSON.getString("url");
                    String title = guideJSON.getString("title");
                    String summary = guideJSON.getString("summary");
                    String difficulty = guideJSON.getString("difficulty");
                    double duration = guideJSON.getDouble("time_required_max");
                    JSONObject imagesJSON = guideJSON.getJSONObject("image");
                    String coverImg = imagesJSON.getString("standard");

                    Guide guide = new Guide(guideId, url, title, summary, difficulty, duration, coverImg);
                    guides.add(guide);
                }
            }
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        return guides;
    }
}
