package com.example.listbooks;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    LayoutInflater inflater;
    ArrayList<Book> library;

    public  MyAdapter(Context context, ArrayList<Book> library) {
        inflater = LayoutInflater.from((context));
        this.library = library;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView author, title, year;
        public ViewHolder(View itemView) {
            super(itemView);
            author = itemView.findViewById(R.id.author);
            title = itemView.findViewById(R.id.title);
            year = itemView.findViewById(R.id.year);
        }

        void bind(int listIndex) {
            author.setText(library.get(listIndex).author);
            title.setText(library.get(listIndex).title);
            year.setText(String.valueOf(library.get(listIndex).year));
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(position);
    }

    public  int getItemCount() {
        return library.size();
    }
}
