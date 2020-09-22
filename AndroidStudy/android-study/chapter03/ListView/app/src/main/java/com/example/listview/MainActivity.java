package com.example.listview;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
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
        setContentView(R.layout.activity_main);

        initData();
        initView();
    }

    private void initView() {
        ListView lvGoods = findViewById(R.id.lv_goods);
        BaseAdapter adapter = new GoodsAdapter(this, goods);

        lvGoods.setAdapter(adapter);
        lvGoods.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Goods good = (Goods) parent.getItemAtPosition(position);
                Toast.makeText(MainActivity.this, good.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    // BaseAdapter要求的数据集合
    private List<Goods> goods;
    private void initData() {
        goods = new ArrayList<>();

        for (int i = 0; i < titles.length; i++) {
            Goods good = new Goods(titles[i], prices[i], icons[i]);
            goods.add(good);
        }
    }

}

//    // SimpleAdapter要求的数据集合
//    private List<Map<String, Object>> datas;
//    private void initSimpleData() {
//        datas = new ArrayList<>();
//        for (int i = 0; i < titles.length; i++) {
//            Map<String, Object> data = new HashMap<>();
//            data.put("title", titles[i]);
//            data.put("price", prices[i]);
//            data.put("icon", icons[i]);
//
//            datas.add(data);
//        }
//    }

//    BaseAdapter adapter = new SimpleAdapter(this,
//                datas,
//                R.layout.list_item,
//                new String[]{"title", "price", "icon"},
//                new int[] {R.id.tv_title, R.id.tv_price, R.id.iv_pic});


//    private void initArrayView() {
//        String[] data={"第1组","第2组","第3组","第4组","第5组","第6组",
//                "第7组","第8组","第9组","第10组","第11组","第12组","第13组",
//                "第14组","第15组","第16组","第17组","第18组","第19组","第20组",
//                "第21组","第22组"};
//
//        ListView lvGoods = findViewById(R.id.lv_goods);
//        ArrayAdapter<String> adapter=new ArrayAdapter<>(
//                MainActivity.this, android.R.layout.simple_list_item_1, data);
//        lvGoods.setAdapter(adapter);
//        lvGoods.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                Toast.makeText(MainActivity.this, "你单击了" + i, Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
