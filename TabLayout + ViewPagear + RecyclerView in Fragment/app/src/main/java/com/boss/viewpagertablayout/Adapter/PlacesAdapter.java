package com.boss.viewpagertablayout.Adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.boss.viewpagertablayout.R;

import java.util.ArrayList;

public class PlacesAdapter extends RecyclerView.Adapter<PlacesAdapter.PlacesHolder> {
    ArrayList<String> arrayList;

    public PlacesAdapter(ArrayList<String> list) {
        this.arrayList = list;
        Log.e("GACT", list.size() + "");
    }

    @NonNull
    @Override
    public PlacesAdapter.PlacesHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PlacesHolder((LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false)));
    }

    @Override
    public void onBindViewHolder(@NonNull PlacesAdapter.PlacesHolder holder, int position) {
        holder.textView.setText(arrayList.get(position));
        Log.e("GACT", arrayList.get(position));
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class PlacesHolder extends RecyclerView.ViewHolder {
        TextView textView;

        public PlacesHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.tv);
        }
    }
}
