package com.boss.retrofitgsonbasics;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.time.chrono.HijrahEra;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static androidx.recyclerview.widget.DividerItemDecoration.HORIZONTAL;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.rv);
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        //Creating a retrofit object
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(HeroAPI.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()) //Here we are using the GsonConverterFactory to directly convert json data to object
                .build();

        // Creating an object of API Interface
        HeroAPI apiInterface = retrofit.create(HeroAPI.class);

        // Interface consists of base URL and all end points at which we are going to do GET,POST,DELETE,PUT..
        // Here, we call the GET heroes method of the API Interface
        Call<List<Hero>> call = apiInterface.getHeros();

        // Execute the call
        call.enqueue(new Callback<List<Hero>>() {
            @Override
            public void onResponse(Call<List<Hero>> call, Response<List<Hero>> response) {
                List<Hero> heroList = response.body();
                Log.e("Tag", heroList.toString());
                HeroAdapter heroAdapter = new HeroAdapter((ArrayList<Hero>) heroList);
                recyclerView.setLayoutManager(linearLayoutManager);
                recyclerView.addItemDecoration(new DividerItemDecoration(MainActivity.this, linearLayoutManager.getOrientation()));
                recyclerView.setAdapter(heroAdapter);
            }

            @Override
            public void onFailure(Call<List<Hero>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
