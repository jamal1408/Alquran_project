package com.example.alquranproject.retrofit;

import com.example.alquranproject.Models.AyatModel.Verses;
import com.example.alquranproject.Models.SurahModel.Chapters;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiEndpoint {
    @GET("chapters?language=id")
    Call<Chapters> getSurah();

    @GET("quran/verses/uthmani")
    Call<Verses> getAyat(@Query("chapter_number") int id);
}
