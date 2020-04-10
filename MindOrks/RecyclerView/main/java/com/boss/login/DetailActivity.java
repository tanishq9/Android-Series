package com.boss.login;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class DetailActivity extends AppCompatActivity {
    TextView textViewTitle, textViewDescription;
    String TAG = "Activity::Detail";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e(TAG, "onCreate Called");
        setContentView(R.layout.activity_detail);
        bindView();
        getIntentData();
        getSupportActionBar().setTitle(textViewTitle.getText().toString());
    }

    private void getIntentData() {
        textViewTitle.setText(getIntent().getStringExtra(AppConstant.TITLE));
        textViewDescription.setText(getIntent().getStringExtra(AppConstant.DESCRIPTION));
    }

    private void bindView() {
        textViewTitle = findViewById(R.id.textViewTitle);
        textViewDescription = findViewById(R.id.textViewDescription);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "onDestroy Called");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e(TAG, "onStop Called");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e(TAG, "onPause Called");
    }


    @Override
    protected void onRestart() {
        super.onRestart();
        Log.e(TAG, "onRestart Called");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e(TAG, "onResume Called");
    }

}
