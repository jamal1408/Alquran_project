package com.example.alquranproject;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.alquranproject.Models.AyatModel.Verses;
import com.example.alquranproject.Models.AyatModel.VersesItem;
import com.example.alquranproject.Models.Terjemahan.Indo;
import com.example.alquranproject.Models.Terjemahan.TranslationsItem;
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
    private List<TranslationsItem> translationsItems = new ArrayList<>();

    List<TranslationsItem> ij;
    List<VersesItem> vi;

    TextView ji, pl, ns;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_surah);

        int id = getIntent().getIntExtra("id", 1);

        String Nama_arab = getIntent().getStringExtra("name_arabic");
        ji = findViewById(R.id.Name_Arabic);
        ji.setText(Nama_arab);

        String Place = getIntent().getStringExtra("revelation_place");
        pl = findViewById(R.id.Tempat_turun);
        pl.setText(Place);

        String N_s = getIntent().getStringExtra("name_complex");
        ns = findViewById(R.id.N_surah);
        ns.setText(N_s);




        setUpView();
        setUpRecyclerView();
        getTranslateData(id);
    }


    private void setUpRecyclerView() {
        adapterAyats = new AdapterAyats(results, translationsItems);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapterAyats);
    }

    private void setUpView() {
        recyclerView = findViewById(R.id.recyclerViewAyat);
    }

    private void getTranslateData(int id) {
        ApiService.endpoint().getIndo(id).enqueue(new Callback<Indo>() {
            @Override
            public void onResponse(Call<Indo> call, Response<Indo> response) {
                if (response.isSuccessful()) {
                    assert response.body() != null;
                    DetailSurahActivity.this.ij = response.body().getTranslations();
                    getDataFromApi(getIntent().getIntExtra( "id",1));
                }
            }
            @Override
            public void onFailure(Call<Indo> call, Throwable t) {
                Log.d("error", t.toString());

            }
        });
    }
    private void getDataFromApi(int id) {
        ApiService.endpoint().getAyat(id).enqueue(new Callback<Verses>() {
            @Override
            public void onResponse(Call<Verses> call, Response<Verses> response) {
                if (response.isSuccessful()) {
                    assert response.body() != null;
                    DetailSurahActivity.this.vi = response.body().getVerses();
                    adapterAyats.setData(ij, vi);
                }
            }
            @Override
            public void onFailure(Call<Verses> call, Throwable t) {

            }


        });
    }}