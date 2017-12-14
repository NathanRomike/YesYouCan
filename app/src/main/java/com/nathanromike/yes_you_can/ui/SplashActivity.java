package com.nathanromike.yes_you_can.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.nathanromike.yes_you_can.Constants;
import com.newrelic.agent.android.NewRelic;

public class SplashActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        NewRelic.withApplicationToken(Constants.NEW_RELIC_APP_TOKEN).start(this.getApplication());

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
