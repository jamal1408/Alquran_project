package com.example.alquranproject;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.alquranproject.Models.Terjemahan.TranslationsItem;

import java.util.List;

public class IndoAdapter extends RecyclerView.Adapter<IndoAdapter.IndoViewHolder> {
    private List<TranslationsItem> result;
    public IndoAdapter(List<TranslationsItem> results){
        this.result = results;
    }

    @NonNull
    @Override
    public IndoAdapter.IndoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new IndoViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.ayat, parent, false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull IndoAdapter.IndoViewHolder holder, int position) {
        TranslationsItem results = result.get(position);

        
        holder.textViewTerjemahanAyat.setText(results.getText());
    }

    @Override
    public int getItemCount() {
        return result.size();
    }
    public class IndoViewHolder extends RecyclerView.ViewHolder {
        TextView textViewTerjemahanAyat;

        public IndoViewHolder(@NonNull View itemView){
            super(itemView);
            textViewTerjemahanAyat = itemView.findViewById(R.id.tvTerjemahanAyat);
        }
    }
    public void setData(List<TranslationsItem> indo){
        result.clear();
        result.addAll(indo);
        notifyDataSetChanged();
    }
}
