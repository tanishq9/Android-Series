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

public class GeneralAdapter extends RecyclerView.Adapter<GeneralAdapter.GeneralHolder> {
    ArrayList<String> arrayList;

    public GeneralAdapter(ArrayList<String> list) {
        this.arrayList = list;
        Log.e("GACT", list.size() + "");
    }

    @NonNull
    @Override
    public GeneralAdapter.GeneralHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new GeneralHolder((LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false)));
    }

    @Override
    public void onBindViewHolder(@NonNull GeneralAdapter.GeneralHolder holder, int position) {
        holder.textView.setText(arrayList.get(position));
        Log.e("GACT", arrayList.get(position));
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class GeneralHolder extends RecyclerView.ViewHolder {
        TextView textView;

        public GeneralHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.tv);
        }
    }
}
