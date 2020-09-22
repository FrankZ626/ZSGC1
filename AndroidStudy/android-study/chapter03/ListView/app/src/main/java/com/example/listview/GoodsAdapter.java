package com.example.listview;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class GoodsAdapter extends BaseAdapter {
    private Context context;
    private List<Goods> datas;

    public GoodsAdapter(Context context, List<Goods> datas) {
        this.context = context;
        this.datas = datas;
    }

    //获取 item的总数
    @Override
    public int getCount() {
        return datas.size();
    }

    // 获取 position位置的item对象
    @Override
    public Object getItem(int position) {
        return datas.get(position);
    }

    // 获取 item的位置
    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            //将 list_item.xml文件找出来并转换成View对象
            convertView = View.inflate(context, R.layout.list_item, null);
            //找到list_item.xml中创建的TextView
            holder = new ViewHolder();
            holder.tvTitle = convertView.findViewById(R.id.tv_title);
            holder.tvPrice = convertView.findViewById(R.id.tv_price);
            holder.ivPic = convertView.findViewById(R.id.iv_pic);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        Goods good = datas.get(position);
        holder.tvTitle.setText(good.getTitle());
        holder.tvPrice.setText(good.getPrice());
        holder.ivPic.setBackgroundResource(good.getIcon());
        return convertView;
    }

    static class ViewHolder {
        TextView tvTitle;
        TextView tvPrice;
        ImageView ivPic;
    }
}