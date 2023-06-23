package com.example.alquranproject.retrofit;

import com.example.alquranproject.Models.AudioModel.Audio;
import com.example.alquranproject.Models.AyatModel.Verses;
import com.example.alquranproject.Models.SurahModel.Chapters;
import com.example.alquranproject.Models.Terjemahan.Indo;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiEndpoint {
    @GET("chapters?language=id")
    Call<Chapters> getSurah();

    @GET ("chapter_recitations/33?")
    Call<Audio> getAudio();

    @GET("quran/verses/uthmani")
    Call<Verses> getAyat(@Query("chapter_number") int id);

    @GET("quran/translations/33?")
    Call<Indo> getIndo (@Query("chapter_number") int id);


}
