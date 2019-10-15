package com.example.motivation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    String[] imageLinks = {
            "https://firebasestorage.googleapis.com/v0/b/motivation-quotes-7c2b0.appspot.com/o/m1.jpg?alt=media&token=d8f4c72e-bbad-41dd-9413-614c7811c936",
            "https://firebasestorage.googleapis.com/v0/b/motivation-quotes-7c2b0.appspot.com/o/m2.jpg?alt=media&token=768b055e-18b9-47ed-8deb-b327d4525d62",
            "https://firebasestorage.googleapis.com/v0/b/motivation-quotes-7c2b0.appspot.com/o/m3.jpg?alt=media&token=d3147914-99a5-4127-9777-f435db822487",
            "https://firebasestorage.googleapis.com/v0/b/motivation-quotes-7c2b0.appspot.com/o/m4.jpg?alt=media&token=86d7865e-665e-43de-a912-333870860cc4",
            "https://firebasestorage.googleapis.com/v0/b/motivation-quotes-7c2b0.appspot.com/o/m5.jpg?alt=media&token=5661ebf1-17df-43e9-b5bd-d6759e3a8b36",
            "https://firebasestorage.googleapis.com/v0/b/motivation-quotes-7c2b0.appspot.com/o/m6.jpg?alt=media&token=ea99fa3c-275b-422a-bb94-d8549b7b99b8",
            "https://firebasestorage.googleapis.com/v0/b/motivation-quotes-7c2b0.appspot.com/o/m7.jpg?alt=media&token=d04c5e31-3fb1-44ef-9550-6da014de3e2a",
            "https://firebasestorage.googleapis.com/v0/b/motivation-quotes-7c2b0.appspot.com/o/m8.jpg?alt=media&token=87d66c11-3387-46c3-93f8-ad72a372ed82"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar=findViewById(R.id.mytoolbar);
        // Do this to make the menu options visible in custom toolbar
        setSupportActionBar(toolbar);

        recyclerView = findViewById(R.id.rv);

        linearLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(linearLayoutManager);

        ArrayList<Quote> quoteList = new ArrayList<>();
        for (String link : imageLinks) {
            quoteList.add(new Quote(link));
        }
        recyclerView.setAdapter(new QuoteAdapter(quoteList, MainActivity.this));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.share:
                return true;
            case R.id.rateus:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
