package com.example.alquranproject;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.alquranproject.Models.AudioModel.Audio;
import com.example.alquranproject.Models.AudioModel.AudioFilesItem;
import com.example.alquranproject.Models.SurahModel.Chapters;
import com.example.alquranproject.Models.SurahModel.ChaptersItem;
import com.example.alquranproject.retrofit.ApiService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private final String TAG = "MainActivity";

    private MainAdapter mainAdapter;
    private AudioAdapter audioAdapter;

    private List<ChaptersItem> surah = new ArrayList<>();

    private List<AudioFilesItem> audio = new ArrayList<>();


    private RecyclerView recyclerView, recyclerView_1;

    private RecyclerView.LayoutManager layoutManager1, layoutManager2;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUpView();
        setUpRecyclerView();
        getDataFromApi();
        getDataFromApiAudio();

    }


    private void setUpRecyclerView() {
//        For Surah
        mainAdapter = new MainAdapter(surah);
        layoutManager1 = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager1);
        recyclerView.setAdapter(mainAdapter);
//        For Audio
        audioAdapter = new AudioAdapter(audio);
        layoutManager2 = new LinearLayoutManager(this);
        recyclerView_1.setLayoutManager(layoutManager2);
        recyclerView_1.setAdapter(audioAdapter);
    }
    private void setUpView() {
        recyclerView = findViewById(R.id.rvSurah);
        recyclerView_1 = findViewById(R.id.rvAudio);
    }

    private void getDataFromApiAudio() {
        ApiService.endpoint().getAudio().enqueue(new Callback<Audio>() {
            @Override
            public void onResponse(Call<Audio> call, Response<Audio> response) {
                if (response.isSuccessful()) {
                    List<AudioFilesItem> result = response.body().getAudioFiles();
                    audioAdapter.setData(result);
                }
            }
            @Override
            public void onFailure(Call<Audio> call, Throwable t) {
            }
        });
    }
    private void getDataFromApi (){
        ApiService.endpoint().getSurah().enqueue(new Callback<Chapters>() {
            @Override
            public void onResponse(Call<Chapters> call, Response<Chapters> response) {
                if (response.isSuccessful()){
                    List<ChaptersItem> result = response.body().getChapters();
                    Log.d(TAG,result.toString());
                    mainAdapter.setData(result);
                }
            }

            @Override
            public void onFailure(Call<Chapters> call, Throwable t) {
                Log.d(TAG, t.toString());
            }
        });
    }
}