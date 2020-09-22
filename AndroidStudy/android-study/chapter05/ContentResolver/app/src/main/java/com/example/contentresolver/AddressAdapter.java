package com.example.contentresolver;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AddressAdapter extends RecyclerView.Adapter<AddressAdapter.ViewHolder> {
    private OnItemClickListener onItemClickListener;
    private List<Address> addresses;
    private int currentPos;

    public AddressAdapter(List<Address> addresses) {
        this.addresses = addresses;
        this.currentPos = -1;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        holder.tvName.setText(addresses.get(position).getName());
        holder.tvPhone.setText(addresses.get(position).getPhone());
        //通过为条目设置点击事件触发回调
        if (onItemClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClickListener.onItemClick(view, position);
                    notifyDataSetChanged();
                }
            });
        }
        if (getCurrentPos() == position) {
            holder.itemView.setBackgroundColor(Color.YELLOW);
        } else {
            holder.itemView.setBackgroundColor(Color.WHITE);
        }
    }

    @Override
    public int getItemCount() {
        return addresses.size();
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }


    public void setCurrentPos(int currentPos) {
        this.currentPos = currentPos;
    }

    public int getCurrentPos() {
        return currentPos;
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvName;
        TextView tvPhone;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_name);
            tvPhone = itemView.findViewById(R.id.tv_phone);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }
}
