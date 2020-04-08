package com.boss.login;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MyNotesActivity extends AppCompatActivity {
    String fullName;
    FloatingActionButton floatingActionButton;
    TextView textViewTitle, textViewDescription;
    EditText editTextTitle, editTextDescription;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);

        bindView();
        setUpSharedPreference();
        getIntentData();

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setUpDialogBox();
            }
        });

        getSupportActionBar().setTitle(fullName);
    }

    private void setUpSharedPreference() {
        sharedPreferences = getSharedPreferences(PrefConstant.SHARED_PREFERENCE_NAME, MODE_PRIVATE);
    }

    private void getIntentData() {
        Intent intent = getIntent();
        fullName = intent.getStringExtra(AppConstant.FULL_NAME);
        if (TextUtils.isEmpty(fullName)) {
            fullName = sharedPreferences.getString(PrefConstant.FULL_NAME, "");
        }
    }

    private void bindView() {
        floatingActionButton = findViewById(R.id.fabAddNote);
        textViewTitle = findViewById(R.id.textViewTitle);
        textViewDescription = findViewById(R.id.textViewDescription);
    }

    private void setUpDialogBox() {
        View view = LayoutInflater.from(MyNotesActivity.this).inflate(R.layout.add_notes_dialog_layout, null);
        editTextTitle = view.findViewById(R.id.editTextTitle);
        editTextDescription = view.findViewById(R.id.editTextDescription);
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setView(view)
                .setCancelable(false)
                .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        updateUI();
                        dialogInterface.dismiss();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                })
                .create();

        dialog.show();
    }

    private void updateUI() {
        textViewTitle.setText(editTextTitle.getText().toString());
        textViewDescription.setText(editTextDescription.getText().toString());
    }
}
