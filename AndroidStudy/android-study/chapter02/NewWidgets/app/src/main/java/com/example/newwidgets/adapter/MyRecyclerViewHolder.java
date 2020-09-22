package com.example.newwidgets.adapter;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.newwidgets.R;

public class MyRecyclerViewHolder extends RecyclerView.ViewHolder {

    public TextView mTextView;

    public MyRecyclerViewHolder(View itemView) {
        super(itemView);
        mTextView = itemView.findViewById(R.id.id_textview);
    }
}
