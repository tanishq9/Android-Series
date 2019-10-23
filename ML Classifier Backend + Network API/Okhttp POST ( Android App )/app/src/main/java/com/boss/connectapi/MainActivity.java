package com.boss.connectapi;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    private static final String BASE_URL = "https://diseases-predict.herokuapp.com/disease/";
    EditText et1, et2, et3, et4, et5, et6, et7, et8;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et1 = findViewById(R.id.et1);
        et2 = findViewById(R.id.et2);
        et3 = findViewById(R.id.et3);
        et4 = findViewById(R.id.et4);
        et5 = findViewById(R.id.et5);
        et6 = findViewById(R.id.et6);
        et7 = findViewById(R.id.et7);
        et8 = findViewById(R.id.et8);
        button = findViewById(R.id.btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (et1.getText().equals("") || et2.getText().equals("") || et3.getText().equals("") ||
                        et4.getText().equals("") || et5.getText().equals("") || et6.getText().equals("") ||
                        et7.getText().equals("") || et8.getText().equals("")) {
                    Toast.makeText(MainActivity.this, "Fill all the fields", Toast.LENGTH_LONG);
                }

                OkHttpClient client = new OkHttpClient();

                RequestBody requestBody = new FormBody.Builder()
                        .add("feat1", et1.getText().toString())
                        .add("feat2", et2.getText().toString())
                        .add("feat3", et3.getText().toString())
                        .add("feat4", et4.getText().toString())
                        .add("feat5", et5.getText().toString())
                        .add("feat6", et6.getText().toString())
                        .add("feat7", et7.getText().toString())
                        .add("feat8", et8.getText().toString())
                        .build();

                Request request = new Request.Builder()
                        .url(BASE_URL + "diabetes")
                        .post(requestBody)
                        .build();

                //initialize the progress dialog and show it
                final ProgressDialog progressDialog = new ProgressDialog(MainActivity.this);
                progressDialog.setMessage("Analyzing");
                progressDialog.show();

                client.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        progressDialog.dismiss();
                        try {
                            String JSONResponse = response.body().string();
                            JSONObject jsonObject = new JSONObject(JSONResponse);
                            final String outcome = jsonObject.getString("outcome");
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    // Make UI Changes
                                    if (outcome.equals("0")) {
                                        Toast.makeText(MainActivity.this, "0 : Positive", Toast.LENGTH_LONG).show();
                                    } else {
                                        Toast.makeText(MainActivity.this, "1 : Negative", Toast.LENGTH_LONG).show();
                                    }
                                }
                            });
                            Log.e("TAG", JSONResponse);
                        } catch (IOException e) {
                            e.printStackTrace();
                            Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_LONG).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });
    }
}
