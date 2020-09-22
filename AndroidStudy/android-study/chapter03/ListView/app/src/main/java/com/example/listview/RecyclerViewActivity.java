package com.example.listview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewActivity extends AppCompatActivity {
    //商品名称与价格数据的集合
    private String[] titles = {"桌子", "苹果", "蛋糕", "线衣", "猕猴桃", "围巾"};
    private String[] prices = {"1800元", "10元/kg", "300元", "350元", "10元/kg", "280元"};
    //图片数据的集合
    private int[] icons = {
            R.drawable.table, R.drawable.apple, R.drawable.cake,
            R.drawable.wireclothes, R.drawable.kiwifruit, R.drawable.scarf};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);
        initData();
        initView();
    }
    private void initView() {
        RecyclerView rvGoods = findViewById(R.id.lv_goods);
        // 设置 RecyclerView的布局管理器
        rvGoods.setLayoutManager(new LinearLayoutManager(this));
        // 设置分割线
        rvGoods.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        // 设置适配器
        MyRecyclerAdapter adapter = new MyRecyclerAdapter(this, goods);
        rvGoods.setAdapter(adapter);
        // 设置监听器
        adapter.setOnItemClickListener(new MyRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Goods good = goods.get(position);
                Toast.makeText(RecyclerViewActivity.this, "点击了" + good, Toast.LENGTH_LONG).show();
            }
        });

    }
    private List<Goods> goods;
    private void initData() {
        goods = new ArrayList<>();

        for (int i = 0; i < titles.length; i++) {
            goods.add(new Goods(titles[i], prices[i], icons[i]));
        }
    }
}