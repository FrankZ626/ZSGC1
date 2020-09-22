package com.example.activity.intent;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnIntent = findViewById(R.id.btn_intent);
        btnIntent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 1. 创建跳转的intent对象
                Intent intent = new Intent(MainActivity.this, IntentActivity.class);
                // 2. 通过intent传递简单类型的数据
                intent.putExtra("班级", "软件1811");
                // 3. 启动Activity
                startActivity(intent);
            }
        });
    }
}


// 2. 通过Bundle传递简单类型的数据
// Bundle bundle = new Bundle();
// bundle.putInt("version", 2);
// intent.putExtras(bundle);


// 3. 通过Bundle传递数组
// int[] nums = {1, 2, 3};
// ArrayList<String> data = new ArrayList<>();
// data.add("软件1811");
// data.add("软件1813");
// data.add("软件1823");
//
// Bundle bundle = new Bundle();
// bundle.putIntArray("nums", nums);
// bundle.putStringArrayList("data", data);
// intent.putExtras(bundle);

// 4. 通过Bundle传递对象
// User user = new User("张海", 20, "软件1811");
// Bundle bundle = new Bundle();
// bundle.putSerializable("user", user);
// intent.putExtras(bundle);

// 5. 通过Bundle传递对象集合
// List<User> users = new ArrayList<>();
// users.add(new User("张海", 20, "软件1811"));
// users.add(new User("王文", 18, "软件1811"));
// users.add(new User("李艳", 19, "软件1811"));
//
// Bundle bundle = new Bundle();
// bundle.putSerializable("users", (Serializable) users);
// intent.putExtras(bundle);
