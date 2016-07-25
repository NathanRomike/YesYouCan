package com.nathanromike.yes_you_can.Services;

import com.nathanromike.yes_you_can.Constants;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public class iFixItService {
    public static void findGuides(String category, Callback callback) {
        OkHttpClient client = new OkHttpClient.Builder()
                .build();

        HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.IFIXIT_BASE_URL).newBuilder();
        urlBuilder.addQueryParameter(category, category);
        String url = urlBuilder.build().toString();

        Request request = new Request.Builder()
                .url(url)
                .build();

        Call call = client.newCall(request);
        call.enqueue(callback);
    }
}
