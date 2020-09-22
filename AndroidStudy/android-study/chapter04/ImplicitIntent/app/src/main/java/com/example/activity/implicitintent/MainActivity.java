package com.example.activity.implicitintent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnSecond = findViewById(R.id.btn_second);
        Button btnCall = findViewById(R.id.btn_call);
        Button btnMessage = findViewById(R.id.btn_message);
        Button btnBrowser = findViewById(R.id.btn_browser);
        Button btnMap = findViewById(R.id.btn_map);

        btnSecond.setOnClickListener(this);
        btnCall.setOnClickListener(this);
        btnMessage.setOnClickListener(this);
        btnBrowser.setOnClickListener(this);
        btnMap.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_second:
                Intent intent = new Intent();
                intent.setAction("com.example.START_ACTIVITY");
                startActivity(intent);
                break;
            case R.id.btn_call:
                callPhone();
                break;
            case R.id.btn_message:
                sendMessage();
                break;
            case R.id.btn_browser:
                openBrowser();
                break;
            case R.id.btn_map:
                openMap();
                break;
        }
    }

    private void callPhone() {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:10086"));
        startActivity(intent);
    }

    private void sendMessage() {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("smsto:10086"));
        intent.putExtra("sms_body","10086, 你好");
        startActivity(intent);
    }

    private void openBrowser() {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("https://www.baidu.com"));
        startActivity(intent);
    }

    private void openMap() {
        Uri uri= Uri.parse("geo:0,0?q=1600+Amphitheatre+Parkway,+Mountain+View,+California");
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);

    }
}