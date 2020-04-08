package com.boss.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;

public class SplashActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
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
        } else {
            startActivity(new Intent(this, LoginActivity.class));
        }
    }
}
