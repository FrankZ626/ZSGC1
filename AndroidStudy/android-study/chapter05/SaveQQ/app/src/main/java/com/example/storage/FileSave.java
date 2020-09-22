package com.example.storage;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;

import androidx.core.app.ActivityCompat;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class FileSave {
    // 保存QQ账号和密码到data.txt文件
    public static boolean saveAccount(Context context, String account, String password) {
        try {
            FileOutputStream fos = context.openFileOutput("data.txt", Context.MODE_PRIVATE);
            fos.write((account + "," + password).getBytes());
            fos.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    // 从data.txt文件中读取QQ账号和密码
    public static Map<String, String> getAccount(Context context) {
        Map<String, String> content = new HashMap<>();
        try {
            FileInputStream fis = context.openFileInput("data.txt");
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            String[] infos = new String(buffer).split(",");
            if (infos.length > 0) {
                content.put("account", infos[0]);
                content.put("password", infos[1]);
            }
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content;
    }

    // 保存到外部存储
    public static Boolean savePublic(Context context, String account, String password) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (context.checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions((Activity) context,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                return false;
            }
        }
        String state = Environment.getExternalStorageState();
        if (state.equals(Environment.MEDIA_MOUNTED)) {
            File SDPath = Environment.getExternalStorageDirectory();
            File file = new File(SDPath, "data.txt");
            try {
                FileOutputStream fos = new FileOutputStream(file);
                fos.write((account + "," + password).getBytes());
                fos.close();
                return true;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    // 保存到外部存储
    public static Map<String, String> getPublic(Context context) {
        Map<String, String> content = new HashMap<>();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (context.checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions((Activity) context,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 2);
                return content;
            }
        }
        String state = Environment.getExternalStorageState();
        if (state.equals(Environment.MEDIA_MOUNTED)) {
            File SDPath = Environment.getExternalStorageDirectory(); //获取SD卡路径
            File file = new File(SDPath, "data.txt");  //创建文件对象
            try {
                FileInputStream fis = new FileInputStream(file);//创建文件输入流对象
                BufferedReader br = new BufferedReader(new InputStreamReader(fis)); //创建输入缓冲流的对象
                String data = br.readLine();   //读取数据
                String[] infos = data.split(",");
                if (infos.length > 0) {
                    content.put("account", infos[0]);
                    content.put("password", infos[1]);
                }
                br.close();  //关闭字符输入缓冲流
                fis.close(); //关闭输入流
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return content;
    }
}

