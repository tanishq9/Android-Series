package com.boss.login.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.boss.login.R;
import com.boss.login.clickListeners.ItemClickListener;
import com.boss.login.model.Note;

import java.util.ArrayList;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NoteHolder> {
    ArrayList<Note> arrayList;
    ItemClickListener itemClickListener;

    public NotesAdapter(ArrayList<Note> list, ItemClickListener itemClickListener) {
        this.arrayList = list;
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public NotesAdapter.NoteHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // responsible for creating the view holder
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.notes_adapter_layout, parent, false);
        return new NoteHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NotesAdapter.NoteHolder holder, int position) {
        // responsible for binding data to the views
        final Note note = arrayList.get(position);
        String title = note.getTitle();
        String description = note.getDescription();
        holder.textViewTitle.setText(title);
        holder.textViewDescription.setText(description);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemClickListener.onClick(note);
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.arrayList.size();
    }

    // viewHolder holds the instances of all views that are filled by the data of the entry
    static class NoteHolder extends RecyclerView.ViewHolder {
        TextView textViewTitle, textViewDescription;

        public NoteHolder(@NonNull View itemView) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.textViewTitle);
            textViewDescription = itemView.findViewById(R.id.textViewDescription);
        }
    }
}

