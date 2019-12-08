package com.boss.qna;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.OpenableColumns;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.nbsp.materialfilepicker.MaterialFilePicker;
import com.nbsp.materialfilepicker.ui.FilePickerActivity;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private static final String ANSWER_URL = "http://192.168.1.4:3000/getAnswers";
    private static final String PDF_URL = "http://192.168.1.4:3000/uploadPDFAndroid";
    private static final int CODE = 123;
    Button button, pdf;
    TextView textView;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.btn);
        pdf = findViewById(R.id.pdf);
        textView = findViewById(R.id.tv);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("KEY", "Button Clicked");
                OkHttpClient.Builder builder = new OkHttpClient.Builder();
                builder.connectTimeout(5, TimeUnit.MINUTES) // connect timeout
                        .writeTimeout(5, TimeUnit.MINUTES) // write timeout
                        .readTimeout(5, TimeUnit.MINUTES); // read timeout

                OkHttpClient client = builder.build();

                Request request = new Request.Builder()
                        .url(ANSWER_URL)
                        .build();

                client.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        Log.e("KEY", String.valueOf(e));
                    }

                    @Override
                    public void onResponse(Call call, final Response response) throws IOException {
                        final String myResponse = response.body().string();
                        Log.e("KEY", myResponse);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                textView.setText(myResponse);
                                Toast.makeText(MainActivity.this, String.valueOf(response), Toast.LENGTH_LONG).show();
                            }
                        });
                    }
                });

            }
        });

        pdf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (ContextCompat.checkSelfPermission(MainActivity.this,
                        Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                    // Do some work
                    uploadPDF();
                    Toast.makeText(MainActivity.this, "Granted Already", Toast.LENGTH_SHORT).show();
                } else {
                    requestStoragePermission();
                }
            }

        });
    }

    private void requestStoragePermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
            new AlertDialog.Builder(this)
                    .setTitle("Permission Needed")
                    .setMessage("These permission are needed because of ..(some reason)..")
                    .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, CODE);
                        }
                    })
                    .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    })
                    .create()
                    .show();
        } else {
            // request permission
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Permission Granted.", Toast.LENGTH_SHORT).show();
                uploadPDF();
            } else {
                Toast.makeText(this, "Permission Denied.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void uploadPDF() {

        new MaterialFilePicker()
                .withActivity(MainActivity.this)
                .withRequestCode(10)
                .start();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable final Intent data) {
        if (requestCode == 10 && resultCode == RESULT_OK) {

            progressDialog = new ProgressDialog(MainActivity.this);
            progressDialog.setTitle("Uploading");
            progressDialog.setMessage("Please Wait ...");
            progressDialog.show();

            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {

                    File file = new File(data.getStringExtra(FilePickerActivity.RESULT_FILE_PATH));
                    // String content_type = getMimeType(file.getPath());

                    String file_path = file.getAbsolutePath();
                    Log.e("PATH", file_path);
                    // aisa kar space ki jagah, chipka de poore file name ko taaki spaces na rhe
                    OkHttpClient okHttpClient = new OkHttpClient();
                    RequestBody file_body = RequestBody.create(MediaType.parse("application/pdf"), file);

                    Log.e("File Name", file_path.substring(file_path.lastIndexOf("/") + 1));

                    RequestBody request_body = new MultipartBody.Builder()
                            .setType(MultipartBody.FORM)
                            .addFormDataPart("type", "application/pdf")
                            .addFormDataPart("file-name", file_path.substring(file_path.lastIndexOf("/") + 1), file_body)
                            .build();

                    Request request = new Request.Builder()
                            .url(PDF_URL)
                            .post(request_body)
                            .build();

                    okHttpClient.newCall(request).enqueue(new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {
                            Log.e("ERROR OKHTTP", String.valueOf(e));
                            progressDialog.dismiss();
                        }

                        @Override
                        public void onResponse(Call call, final Response response) throws IOException {
                            final String myResponse = response.body().string();
                            Log.e("RESPONSE OKHTTP", myResponse);
                            progressDialog.dismiss();
                        }
                    });


                }
            });
            thread.start();
        }
    }

    private String getMimeType(String path) {
        String extension = MimeTypeMap.getFileExtensionFromUrl(path);
        return MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension);
    }
}
