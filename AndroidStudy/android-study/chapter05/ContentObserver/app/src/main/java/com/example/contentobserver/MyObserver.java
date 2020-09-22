package com.example.contentobserver;

import android.database.ContentObserver;
import android.os.Handler;
import android.util.Log;

public class MyObserver extends ContentObserver {
    public MyObserver(Handler handler) {
        super(handler);
    }

    @Override
    public void onChange(boolean selfChange) {
        super.onChange(selfChange);
        Log.i("监测数据变化", "database changed");
    }
}
