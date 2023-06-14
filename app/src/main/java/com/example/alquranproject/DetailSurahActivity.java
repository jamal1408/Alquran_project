package com.example.alquranproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.alquranproject.Models.AyatModel.Verses;
import com.example.alquranproject.Models.AyatModel.VersesItem;
import com.example.alquranproject.Models.SurahModel.Chapters;
import com.example.alquranproject.retrofit.ApiService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailSurahActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private AdapterAyats adapterAyats;
    private List<VersesItem> results = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_surah);


        int id = getIntent().getIntExtra("id", 1);

        setUpView();
        setUpRecyclerView();
        System.out.println(id);
        getDataFromApi(id);
    }

    private void setUpRecyclerView() {
        adapterAyats = new AdapterAyats(results);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapterAyats);
    }

    private void setUpView() {
        recyclerView = findViewById(R.id.recyclerViewAyat);
    }

    private void getDataFromApi(int id) {
        ApiService.endpoint().getAyat(id).enqueue(new Callback<Verses>() {
            @Override
            public void onResponse(Call<Verses> call, Response<Verses> response) {
                if (response.isSuccessful()) {
                    List<VersesItem> result = response.body().getVerses();
                    adapterAyats.setData(result);
                }
            }

            @Override
            public void onFailure(Call<Verses> call, Throwable t) {

            }
        });
    }}