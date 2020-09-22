package com.example.activity.intent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class IntentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent);

        TextView tvData = findViewById(R.id.tv_data);

        // 1. 接收主界面传递的简单值
        Intent intent = getIntent();
        String classmate = intent.getStringExtra("班级");
        tvData.setText("通过Intent传递简单值：classmate=" + classmate);
    }
}

// 2. 接收Bundle传递的简单值
// Bundle bundle = intent.getExtras();
// if(bundle != null) {
//     int version = bundle.getInt("version");
//     tvData.setText("通过Bundle传递简单值：version=" + version);
// }

// 3. 接收Bundle传递的数组
// if(bundle != null) {
//     int[] nums = bundle.getIntArray("nums");
//     ArrayList<String> data = bundle.getStringArrayList("data");
//     tvData.setText("通过Bundle传递数组：nums=" + nums.toString() + "data=" + data.toString());
// }

// 4. 接收对象、对象集合
// if(bundle != null) {
//     User user = (User) bundle.getSerializable("user");
//     tvData.setText("通过Bundle传递对象：user: " + user.toString());
// }

// 5. 接收对象集合
// if(bundle != null) {
//     List<User> users = (List<User>) bundle.getSerializable("users");
//     tvData.setText("通过Bundle传递对象列表：users=" + users.toString());
// }
