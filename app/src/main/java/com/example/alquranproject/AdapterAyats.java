package com.example.alquranproject;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.alquranproject.Models.AyatModel.VersesItem;
import com.example.alquranproject.Models.Terjemahan.TranslationsItem;

import java.util.List;

public class AdapterAyats extends RecyclerView.Adapter<AdapterAyats.AyatViewHolder> {
    private List<VersesItem> results;

    private List<TranslationsItem> translationsItems;

    public AdapterAyats(List<VersesItem> results , List<TranslationsItem> translationsItems){

        this.results = results;
        this.translationsItems = translationsItems;
    }

    @NonNull
    @Override
    public AyatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AyatViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.ayat, parent, false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull AyatViewHolder holder, int position) {
        TranslationsItem translationsItem = translationsItems.get(position);
        VersesItem result = results.get(position);
        holder.textViewAyat.setText(result.getTextUthmani());
        holder.TerjemahanAyat.setText(translationsItem.getText());

    }

    @Override
    public int getItemCount() {
        return results.size();
    }

    public class AyatViewHolder extends RecyclerView.ViewHolder {
        TextView textViewAyat;
        TextView TerjemahanAyat;
        public AyatViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewAyat = itemView.findViewById(R.id.tvAyat);
            TerjemahanAyat = itemView.findViewById(R.id.tvTerjemahanAyat);
        }
    }

    public void setData(List<TranslationsItem> ti, List<VersesItem> result){
        translationsItems.clear();
        translationsItems.addAll(ti);

        results.clear();
        results.addAll(result);
        notifyDataSetChanged();
    }


}
