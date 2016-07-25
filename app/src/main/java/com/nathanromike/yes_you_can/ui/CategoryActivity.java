package com.nathanromike.yes_you_can.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.nathanromike.yes_you_can.Constants;
import com.nathanromike.yes_you_can.R;
import com.nathanromike.yes_you_can.Services.iFixItService;
import com.nathanromike.yes_you_can.models.Guide;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class CategoryActivity extends AppCompatActivity {
    public static final String TAG = CategoryActivity.class.getSimpleName();

    @BindView(R.id.listView) ListView mListView;

    public ArrayList<Guide> mGuides = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        ButterKnife.bind(this);
        getGuides(Constants.ELECTRONIC);
    }

    private void getGuides(String category) {
        final iFixItService fixItService = new iFixItService();
        fixItService.findGuides(category, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    mGuides = fixItService.processResults(response);

                    CategoryActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            String[] titles = new String[mGuides.size()];
                            for (int i = 0; i < titles.length; i++) {
                                titles[i] = mGuides.get(i).getTitle();
                            }

                            ArrayAdapter adapter = new ArrayAdapter(CategoryActivity.this, android.R.layout.simple_list_item_1, titles);
                            mListView.setAdapter(adapter);
                        }
                    });
                }
            }
        });
    }
}
