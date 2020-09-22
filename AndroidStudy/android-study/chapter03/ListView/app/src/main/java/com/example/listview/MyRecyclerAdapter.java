package com.example.listview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.MyViewHolder> {
    private Context context;
    private List<Goods> goods;

    // item事件监听器对象
    private OnItemClickListener onItemClickListener;

    public MyRecyclerAdapter(Context context, List<Goods> goods) {
        this.context = context;
        this.goods = goods;
    }

    // 注册绑定 list_view，创建并返回 ViewHolder对象
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
        return new MyViewHolder(view);
    }

    // 绑定数据
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        Goods good = goods.get(position);
        holder.tvTitle.setText(good.getTitle());
        holder.tvPrice.setText(good.getPrice());
        holder.ivPic.setBackgroundResource(good.getIcon());

        // 通过为item设置点击事件触发回调
        if (onItemClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClickListener.onItemClick(view, position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return goods.size();
    }

    // 设置监听器
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle;
        TextView tvPrice;
        ImageView ivPic;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            // 获取控件对象
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvPrice = itemView.findViewById(R.id.tv_price);
            ivPic = itemView.findViewById(R.id.iv_pic);
        }
    }

    // item的事件监听接口
    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }
}
