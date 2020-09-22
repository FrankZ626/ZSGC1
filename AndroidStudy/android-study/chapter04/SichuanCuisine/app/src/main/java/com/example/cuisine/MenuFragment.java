package com.example.cuisine;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MenuFragment extends Fragment {
    private int[] settingIcon;
    private String[] foodNames;
    private String[] settingText;
    private RecyclerView rvMenus;
    private MainActivity activity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //解析布局
        View view = inflater.inflate(R.layout.fragment_menu, container, false);

        //获取Acitivty实例对象
        activity = (MainActivity) getActivity();
        //获取Activity中的图标数组
        settingIcon = activity.getIcons();
        //获取Activity中定义的川菜名称
        foodNames = activity.getNames();
        //获取Activity中的设置文字数组
        settingText = activity.getSettingText();

        // 如果view不为空
        if (view != null) {
            rvMenus = view.findViewById(R.id.rv_menus);
            rvMenus.setLayoutManager(new LinearLayoutManager(activity));
            rvMenus.addItemDecoration(new DividerItemDecoration(activity, DividerItemDecoration.VERTICAL));

            MyAdapter adapter = new MyAdapter();
            // 设置条目监听
            adapter.setOnItemClickListener(new OnItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {
                    ContentFragment listFragment = (ContentFragment) activity.getSupportFragmentManager().findFragmentById(R.id.food_content);
                    //设置菜品类表点击位置对应的菜品做法的Fragment文字
                    listFragment.setText(settingText[position]);
                }
            });
            if (settingIcon != null) {
                rvMenus.setAdapter(adapter);
            }
        }
        return view;
    }

    //适配器
    class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
        private OnItemClickListener itemClickListener;

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = View.inflate(parent.getContext(), R.layout.item_list, null);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
            holder.ivName.setBackgroundResource(settingIcon[position]);
            holder.tvFoodName.setText(foodNames[position]);

            //通过为条目设置点击事件触发回调
            if (itemClickListener != null) {
                holder.itemLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        itemClickListener.onItemClick(view, position);
                    }
                });
            }
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public int getItemCount() {
            return settingIcon.length;
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            ImageView ivName;
            TextView tvFoodName;
            RelativeLayout itemLayout;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                ivName = itemView.findViewById(R.id.food_icon);
                tvFoodName = itemView.findViewById(R.id.food_name);
                itemLayout = itemView.findViewById(R.id.item_layout);
            }
        }

        public void setOnItemClickListener(OnItemClickListener itemClickListener) {
            this.itemClickListener = itemClickListener;
        }
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }
}
