package com.example.storage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.storage.R;

import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText etAccount;
    private EditText etPassword;
    private Button btnLogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initAccount();
    }

    private void initAccount() {
        Map<String, String> data = SPSave.getAccount(this);
//        Map<String, String> data = FileSave.getAccount(this);
//        Map<String, String> data = FileSave.getPublic(this);
        if(data.size() > 0) {
            etAccount.setText(data.get("account"));
            etPassword.setText(data.get("password"));
        }
    }

    private void initView() {
        etAccount = findViewById(R.id.et_account);
        etPassword = findViewById(R.id.et_password);
        btnLogin = findViewById(R.id.btn_login);
        btnLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String account = etAccount.getText().toString().trim();
        String password = etPassword.getText().toString();

        if(TextUtils.isEmpty(account)) {
            Toast.makeText(this, "请输入QQ账号", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(password)) {
            Toast.makeText(this, "请输入密码", Toast.LENGTH_SHORT).show();
            return;
        }

        boolean isSuccess = SPSave.saveAccount(this, account, password);
//        boolean isSuccess = FileSave.saveAccount(this, account, password);
//        boolean isSuccess = FileSave.savePublic(this, account, password);
        if(isSuccess) {
            Toast.makeText(this, "保存成功", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "保存失败", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(grantResults.length == 0 || grantResults[0] != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "权限申请被拒绝", Toast.LENGTH_SHORT).show();
            return;
        }
        switch (requestCode) {
            case 1:
                boolean isSuccess = FileSave.savePublic(this, etAccount.getText().toString(), etPassword.getText().toString());
                if(isSuccess) {
                    Toast.makeText(this, "保存成功", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "保存失败", Toast.LENGTH_SHORT).show();
                }
                break;
            case 2:
                Map<String, String> data = FileSave.getPublic(this);
                if(data.size() > 0) {
                    etAccount.setText(data.get("account"));
                    etPassword.setText(data.get("password"));
                }
                break;
        }
    }
}