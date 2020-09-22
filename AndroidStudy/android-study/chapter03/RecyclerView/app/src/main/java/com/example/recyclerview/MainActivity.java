package com.example.recyclerview;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private String[] names = {"小猫", "哈士奇", "小黄鸭", "小鹿", "老虎"};
    private int[] icons = {R.drawable.cat, R.drawable.siberiankusky,
            R.drawable.yellowduck, R.drawable.fawn, R.drawable.tiger};
    private String[] introduces = {
            "猫，属于猫科动物，分家猫、野猫，是全世界家庭中较为广泛的宠物。",
            "西伯利亚雪橇犬，常见别名哈士奇，昵称为二哈。",
            "鸭的体型相对较小，颈短，一些属的嘴要大些。腿位于身体后方，因而步态蹒跚。",
            "鹿科是哺乳纲偶蹄目下的一科动物。体型大小不等，为有角的反刍类。",
            "虎，大型猫科动物；毛色浅黄或棕黄色，满有黑色横纹；头圆、耳短，耳背面黑色，中央有一白斑甚显著；四肢健壮有力；尾粗长，具黑色环纹，尾端黑色。"};

    private AnimalAdapter adapter;
    private RecyclerView rvAnimals;
    private List<Animal> datas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initData();
        initView();
    }

    private void initView() {
        rvAnimals = findViewById(R.id.rv_goods);
        rvAnimals.setLayoutManager(new LinearLayoutManager(this));
        rvAnimals.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        adapter = new AnimalAdapter(datas);
        adapter.setOnItemClickListener(new AnimalAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(MainActivity.this, "点击了" + datas.get(position), Toast.LENGTH_LONG).show();
            }
        });
        rvAnimals.setAdapter(adapter);
    }

    private void initData() {
        datas = new ArrayList<>();

        for (int i = 0; i < names.length; i++) {
            Animal animal = new Animal(names[i], introduces[i], icons[i]);
            datas.add(animal);
        }
    }
}