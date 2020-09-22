package com.example.contentprovider;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private TextView tvContent;
    private TextView tvDes;
    private List<SmsInfo> smses;
    private List<ContactInfo> contacts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        smses = new ArrayList<>();
        contacts = new ArrayList<>();
    }

    private void initView() {
        tvDes = findViewById(R.id.tv_des);
        tvContent = findViewById(R.id.tv_content);

        Button btnSms = findViewById(R.id.btn_sms);
        btnSms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityCompat.requestPermissions(MainActivity.this,
                        new String[]{Manifest.permission.READ_SMS}, 1);
            }
        });

        Button btnContact = findViewById(R.id.btn_contact);
        btnContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityCompat.requestPermissions(MainActivity.this,
                        new String[]{Manifest.permission.READ_CONTACTS}, 2);

            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (grantResults.length == 0 || grantResults[0] != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "权限申请被拒绝", Toast.LENGTH_SHORT).show();
            return;
        }

        if(requestCode == 1) {
            getSms();
        }else if(requestCode == 2) {
            getContacts();
        }
    }

    private void getSms() {
        Uri uri = Uri.parse("content://sms/");
        Cursor cursor = getContentResolver().query(uri, new String[]{"_id", "address", "body"}, null, null, null);
        if(cursor != null) {
            tvDes.setVisibility(View.VISIBLE);
            if(smses != null && smses.size() > 0) {
                smses.clear();
            }
            while (cursor.moveToNext()) {
                int _id = cursor.getInt(0);
                String address = cursor.getString(1);
                String body = cursor.getString(2);
                smses.add(new SmsInfo(_id, address, body));
            }
            cursor.close();
        }
        StringBuilder text = new StringBuilder();
        for(SmsInfo sms : smses) {
            text.append("手机号码：").append(sms.getAddress()).append("\n");
            text.append("短信内容：").append(sms.getBody()).append("\n\n");
        }
        tvContent.setText(text.toString());
    }

    private void getContacts() {
        Cursor cursor = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                new String[] {"display_name", "data1"}, null, null, null);
        if(cursor != null) {
            tvDes.setVisibility(View.VISIBLE);
            if(contacts != null && contacts.size() > 0) {
                contacts.clear();
            }
            int i = 0;
            while (cursor.moveToNext()) {
                // 获取联系人的姓名
                String displayName = cursor.getString(cursor.getColumnIndex(
                        ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                // 获取联系人的手机号码
                String number = cursor.getString(cursor.getColumnIndex(
                        ContactsContract.CommonDataKinds.Phone.NUMBER));
                contacts.add(new ContactInfo(i++, displayName, number));
            }
            cursor.close();
        }
        StringBuilder text = new StringBuilder();
        for(ContactInfo contact : contacts) {
            text.append("姓名：").append(contact.getName()).append("\n");
            text.append("手机号：").append(contact.getNumber()).append("\n\n");
        }
        tvContent.setText(text.toString());

    }
}