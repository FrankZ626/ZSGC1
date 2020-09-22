package com.example.storage;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashMap;
import java.util.Map;

public class SPSave {
    // 保存QQ账号和密码
    public static boolean saveAccount(Context context, String account, String password) {
        SharedPreferences sp = context.getSharedPreferences("data", Context.MODE_PRIVATE);
        sp.edit().putString("account", account)
                .putString("password", password)
                .apply();
        return true;
    }

    // 获取存储的QQ账号和密码
    public static Map<String, String> getAccount(Context context) {
        SharedPreferences sp = context.getSharedPreferences("data", Context.MODE_PRIVATE);
        Map<String, String> data = new HashMap<>();
        data.put("account", sp.getString("account", null));
        data.put("password", sp.getString("password", null));
        return data;
    }
}
