package com.boss.retrofitgsonbasics;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class HeroAdapter extends RecyclerView.Adapter<HeroAdapter.HeroHolder> {
    ArrayList<Hero> arrayList;

    HeroAdapter(ArrayList<Hero> list) {
        this.arrayList = list;
    }

    @NonNull
    @Override
    public HeroHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        HeroHolder heroHolder = new HeroHolder(view);
        return heroHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull HeroHolder holder, int position) {
        holder.textView.setText(arrayList.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class HeroHolder extends RecyclerView.ViewHolder {
        TextView textView;

        public HeroHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.tv);
        }
    }
}
