package com.boss.retrofitgsonbasics;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface HeroAPI {
    String BASE_URL = "https://simplifiedcoding.net/demos/";

    @GET("marvel")
    Call<List<Hero>> getHeros();
}
