package com.boss.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.w3c.dom.Text;

public class LoginActivity extends AppCompatActivity {
    EditText editTextFullName, editTextUserName;
    Button buttonLogin;
    SharedPreferences sharedPreferences;
    String TAG = "Activity::Login";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // activity are used to manage user interactions and what is displayed on the screen
        super.onCreate(savedInstanceState);
        Log.e(TAG, "onCreate Called");
        setContentView(R.layout.activity_login);

        bindView();
        setUpSharedPreferences();

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fullName = editTextFullName.getText().toString();
                String userName = editTextUserName.getText().toString();
                if (!TextUtils.isEmpty(fullName) && !TextUtils.isEmpty(userName)) {
                    // intent are used to perform action and pass data
                    Intent intent = new Intent(LoginActivity.this, MyNotesActivity.class);
                    intent.putExtra(AppConstant.FULL_NAME, fullName);
                    startActivity(intent);
                    // after login, save login status
                    saveLoginStatus();
                    // save full name
                    saveFullName(fullName);
                    // finish this activity
                    finish();
                } else {
                    Toast.makeText(LoginActivity.this, "Enter both the field(s)", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void saveFullName(String fullName) {
        sharedPreferences.edit().putString(PrefConstant.FULL_NAME, fullName).apply();
    }

    private void saveLoginStatus() {
        sharedPreferences.edit().putBoolean(PrefConstant.IS_LOGGED_IN, true).apply();
    }

    private void setUpSharedPreferences() {
        sharedPreferences = getSharedPreferences(PrefConstant.SHARED_PREFERENCE_NAME, MODE_PRIVATE);
    }

    private void bindView() {
        editTextFullName = findViewById(R.id.editTextFullName);
        editTextUserName = findViewById(R.id.editTextUserName);
        buttonLogin = findViewById(R.id.buttonLogin);
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
