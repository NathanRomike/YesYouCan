package com.nathanromike.yes_you_can.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.nathanromike.yes_you_can.R;
import com.nathanromike.yes_you_can.Services.iFixItService;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class CategoryActivity extends AppCompatActivity {
    public static final String TAG = CategoryActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        getGuides("Electronics");
    }

    private void getGuides(String category) {
        final iFixItService fixItService = new iFixItService();
        fixItService.findGuides(category, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.v(TAG, e.toString());
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try {
                    String jsonData = response.body().string();
                    Log.v(TAG, jsonData);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
