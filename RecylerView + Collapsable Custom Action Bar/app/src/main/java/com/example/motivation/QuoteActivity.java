package com.example.motivation;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class QuoteActivity extends AppCompatActivity {
    private static final int CODE_SHARE = 123;
    private static final int CODE_DOWNLOAD = 456;
    ImageView imageView;
    Button download;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quote);
        imageView = findViewById(R.id.iv);
        download = findViewById(R.id.download);
        Intent intent = getIntent();
        String imageUrl = intent.getStringExtra("key");
        Glide.with(this)
                .load(imageUrl)
                .placeholder(R.drawable.progress_animation)
                .error(R.drawable.no_wifi)
                .into(imageView);

        download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("QuoteAct", "Download Button Clicked");

                if (ContextCompat.checkSelfPermission(QuoteActivity.this,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED
                        &&
                        ContextCompat.checkSelfPermission(QuoteActivity.this,
                                Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED
                ) {
                    // Do some work, save in Internal Storage
                    saveImageToGallery();
                } else {
                    requestStoragePermission(CODE_DOWNLOAD);
                }
            }
        });
    }

    private void requestStoragePermission(final int CODE) {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                && ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE
        )) {

            new AlertDialog.Builder(this, R.style.AlertDialogTheme)
                    .setTitle("Permission Needed")
                    .setMessage("This permission is needed to save the image in your device.")
                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            ActivityCompat.requestPermissions(QuoteActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}, CODE);
                        }
                    })
                    .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    })
                    .create()
                    .show();
        } else {
            // request permission
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}, CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == CODE_DOWNLOAD) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED
                    && grantResults[1] == PackageManager.PERMISSION_GRANTED
            ) {
                saveImageToGallery();
            } else {
                Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void saveImageToGallery() {
        imageView.setDrawingCacheEnabled(true);
        Bitmap bitmap = imageView.getDrawingCache();
        MediaStore.Images.Media.insertImage(QuoteActivity.this.getContentResolver(), bitmap, "image", "");
        Toast.makeText(QuoteActivity.this, "Image Saved Successfully", Toast.LENGTH_SHORT).show();
    }
}
