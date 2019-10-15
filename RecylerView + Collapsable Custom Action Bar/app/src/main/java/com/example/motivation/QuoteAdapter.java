package com.example.motivation;

import android.content.Context;
import android.media.Image;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class QuoteAdapter extends RecyclerView.Adapter<QuoteAdapter.QuoteHolder> {
    ArrayList<Quote> quoteArrayList;
    Context context;
    public QuoteAdapter(ArrayList<Quote> quotes,Context c){
        this.quoteArrayList=quotes;
        this.context=c;
    }

    @NonNull
    @Override
    public QuoteAdapter.QuoteHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new QuoteHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull QuoteAdapter.QuoteHolder holder, int position) {
        Quote quote=quoteArrayList.get(position);
        Log.e("TAG",quote.getImageUrl());
        Picasso.get().load(quote.getImageUrl())
                .placeholder(R.drawable.progress_animation)
                .error(R.drawable.no_wifi)
                .networkPolicy(NetworkPolicy.OFFLINE)//use this for offline support
                .into(holder.imageView, new Callback() {
                    @Override
                    public void onSuccess() {
                        Log.e("TAG","Image Loaded.");
                    }

                    @Override
                    public void onError(Exception e) {
                        Log.e("TAG",e.getMessage());
                    }
                });
    }

    @Override
    public int getItemCount() {
        return quoteArrayList.size();
    }

    // ViewHolder holds the instances of all views that are filled by the data of the entry
    public class QuoteHolder extends RecyclerView.ViewHolder{
        ImageView imageView;

        public QuoteHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.iv);
            // Add onClick functionality
        }
    }
}
