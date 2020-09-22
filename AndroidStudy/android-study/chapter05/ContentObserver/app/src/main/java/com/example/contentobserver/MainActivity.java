package com.example.contentobserver;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Uri uri = Uri.parse("content://com.example.contentprovider/information");
        // 注册内容观察者
        getContentResolver().registerContentObserver(uri, true, new MyObserver(new Handler()));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 取消注册内容观察者、
        getContentResolver().unregisterContentObserver(new MyObserver(new Handler()));
    }
}