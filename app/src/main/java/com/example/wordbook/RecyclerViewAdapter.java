package com.example.wordbook;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    Context context;
    ArrayList<Word> list;

    public RecyclerViewAdapter(Context context, ArrayList<Word> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.MyViewHolder holder, int position) {

        Word currentWord = list.get(position);
        holder.date.setText(currentWord.getDate());
        holder.meaning.setText(currentWord.getMeaning());
        holder.word.setText(currentWord.getWord());
        holder.source.setText(currentWord.getSource());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView word, meaning, source, date;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            word = itemView.findViewById(R.id.rvWord);
            meaning = itemView.findViewById(R.id.rvMeaning);
            source = itemView.findViewById(R.id.rvSource);
            date = itemView.findViewById(R.id.rvDate);

        }
    }
}
