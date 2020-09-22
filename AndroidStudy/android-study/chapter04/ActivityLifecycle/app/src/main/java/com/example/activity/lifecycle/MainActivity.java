package com.example.activity.lifecycle;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {
    private final String TAG = "MainActivity";

    // The activity is being created.
    // Activity的整个生命周期发生在onCreate()与onDestroy()之间
    // onCreate()完成全局的状态设置，如：定义布局，并释放onDestroy()中的所有其余资源
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.i(TAG, "onCreate() is called");
    }

    // The activity is about to become visible.
    // Activity的可见生命周期发生在onStart()与onStop()之间
    // Activity变得可见，并可以交互
    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "onStart() is called");
    }

    // The activity has become visible (it is now "resumed").
    // Activity的前台生命周期发生在onResume()和onPause()之间
    // 在此期间，Activity位于屏幕的所有Activity之前，并具有用户输入焦点
    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "onResume() is called");
    }

    // Another activity is taking focus (this activity is about to be "paused").
    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "onPause() is called");
    }

    // The activity is no longer visible (it is now "stopped")
    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "onStop() is called");
    }

    // The activity is about to be destroyed.
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy() is called");
    }
}