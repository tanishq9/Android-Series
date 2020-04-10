package com.boss.login;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.boss.login.adapter.NotesAdapter;
import com.boss.login.clickListeners.ItemClickListener;
import com.boss.login.model.Note;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MyNotesActivity extends AppCompatActivity {
    String fullName;
    FloatingActionButton floatingActionButton;
    EditText editTextTitle, editTextDescription;
    RecyclerView recyclerView;
    SharedPreferences sharedPreferences;
    ArrayList<Note> arrayList;
    LinearLayoutManager linearLayoutManager;
    NotesAdapter notesAdapter;
    ItemClickListener itemClickListener;
    String TAG = "Activity::Notes";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e(TAG, "onCreate Called");
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
        recyclerView = findViewById(R.id.recyclerView);
        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        itemClickListener = new ItemClickListener() {
            @Override
            public void onClick(Note note) {
                Intent intent = new Intent(MyNotesActivity.this, DetailActivity.class);
                intent.putExtra(AppConstant.TITLE, note.getTitle());
                intent.putExtra(AppConstant.DESCRIPTION, note.getDescription());
                startActivity(intent);
            }
        };
        arrayList = new ArrayList<>();
        notesAdapter = new NotesAdapter(arrayList, itemClickListener);
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

                        String title = editTextTitle.getText().toString();
                        String description = editTextDescription.getText().toString();
                        if (!TextUtils.isEmpty(title) && !TextUtils.isEmpty(description)) {
                            // create a new note
                            arrayList.add(new Note(editTextTitle.getText().toString(), editTextDescription.getText().toString()));
                            // update the UI by updating the adapter
                            updateUI();
                        } else {
                            Toast.makeText(MyNotesActivity.this, "Enter both title and description", Toast.LENGTH_SHORT).show();
                        }
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
        recyclerView.setAdapter(notesAdapter);
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
