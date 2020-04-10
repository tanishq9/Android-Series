package com.boss.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;

public class SplashActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    String TAG = "Activity::Splash";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Log.e(TAG, "onCreate Called");
        setUpSharedPreferences();
        checkLoginStatus();
    }

    private void setUpSharedPreferences() {
        sharedPreferences = getSharedPreferences(PrefConstant.SHARED_PREFERENCE_NAME, MODE_PRIVATE);
    }

    private void checkLoginStatus() {
        // if user is logged in -> MyNotesActivity
        // else LoginActivity
        boolean isLoggedIn = sharedPreferences.getBoolean(PrefConstant.IS_LOGGED_IN, false);
        if (isLoggedIn) {
            startActivity(new Intent(this, MyNotesActivity.class));
            finish();
        } else {
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        }
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
