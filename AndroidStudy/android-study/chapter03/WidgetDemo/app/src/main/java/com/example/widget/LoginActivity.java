package com.example.widget;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity
        implements View.OnClickListener {
    // 定义控件变量名称
    private EditText etUsername;
    private EditText etPassword;
    private CheckBox cbAutoLogin;
    private Button btnLogin;

    private boolean isAutoLogin = false;

    // 1. 获取界面控件对象
    // 2. 获取用户名、密码的值
    // 3. 当登录按钮点击时，处理登录的逻辑
    // 4. 根据登录成功与否给出提示信息
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // 1. 获取用户名、密码和按钮等控件对象
        etUsername = findViewById(R.id.et_username);
        etPassword = findViewById(R.id.et_password);
        cbAutoLogin = findViewById(R.id.cb_auto_login);
        btnLogin = findViewById(R.id.btn_login);

        if(cbAutoLogin.isChecked()) {
            isAutoLogin = true;
            // 将自动登录信息存在本地
        }

        // 2. 监听按钮的点击事件
//        btnLogin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // 3. 处理按钮的点击事件
//                // 3.1 获取用户名和密码的值
//                String username = etUsername.getText().toString().trim();
//                String password = etPassword.getText().toString();
//                // 3.2. 比较用户名和密码是否正确，然后给出提示
//                if(username.equals("android")&&password.equals("123456")) {
//                    Toast.makeText(MainActivity.this, "登录成功", Toast.LENGTH_LONG).show();
//                } else {
//                    Toast.makeText(MainActivity.this, "用户名或密码不对", Toast.LENGTH_LONG).show();
//                }
//            }
//        });

        btnLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_login) {
            login();
        }
    }

    private void login() {
        // 3.1 获取用户名和密码的值
        String username = etUsername.getText().toString().trim();
        String password = etPassword.getText().toString();
        // 3.2. 比较用户名和密码是否正确，然后给出提示
        if ("android".equals(username) && "123456".equals(password)) {
            Toast.makeText(LoginActivity.this, "登录成功",
                    Toast.LENGTH_LONG).show();
            Intent intent = new Intent(LoginActivity.this, InfoActivity.class);
            intent.putExtra("username", username);
            startActivity(intent);
        } else {
            Toast.makeText(LoginActivity.this, "用户名或密码不正确",
                    Toast.LENGTH_LONG).show();
        }
    }
}
