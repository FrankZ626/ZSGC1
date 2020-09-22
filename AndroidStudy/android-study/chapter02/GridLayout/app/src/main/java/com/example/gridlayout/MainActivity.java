package com.example.gridlayout;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.googlecode.aviator.AviatorEvaluator;

public class MainActivity extends AppCompatActivity {
    // 1. 定义界面上需要的控件对象
    private TextView tvInput;
    private TextView tvResult;

    // 需要的变量定义
    private String inputStr;  // 存储输入的数字等的内容的字符串
    private String express;   // 表示真实的表达式
    private boolean flag;     // 清除TextView内容的标志，按了"="之后清除

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_calculator);

        // 2. 获取界面的控件对象
        tvInput = findViewById(R.id.tv_input);
        tvResult = findViewById(R.id.tv_result);

        flag = false;
        express = "";
        inputStr = "";
    }

    // button按钮的事件处理的一种方式
    public void calculate(View view) {
        Button button = (Button) view;
        if (flag) {
            inputStr = "";
            tvInput.setText(inputStr);
            tvResult.setText("0");
            flag = !flag;
        }
        switch(view.getId()) {
            case R.id.btn_0:
            case R.id.btn_00:
            case R.id.btn_1:
            case R.id.btn_2:
            case R.id.btn_3:
            case R.id.btn_4:
            case R.id.btn_5:
            case R.id.btn_6:
            case R.id.btn_7:
            case R.id.btn_8:
            case R.id.btn_9:
            case R.id.btn_dot:
            case R.id.btn_add:
            case R.id.btn_sub:
                inputStr += button.getText().toString();
                express += button.getText().toString();
                break;
            case R.id.btn_multi:
                inputStr += button.getText().toString();
                express += "*";
                break;
            case R.id.btn_div:
                inputStr += button.getText().toString();
                express += "/";
                break;
            case R.id.btn_c:
                inputStr = "";
                express = "";
                break;
            case R.id.btn_del:
                if(inputStr.length() > 0) {
                    inputStr = inputStr.substring(0, inputStr.length() - 1);
                    express = express.substring(0, express.length() - 1);
                }
                break;
            case R.id.btn_equal:
                if (express.length() > 0) {
                    long result = (long) AviatorEvaluator.execute(express);
                    tvResult.setText(String.valueOf(result));
                }
                flag = true;
                express = "";
                break;
            case R.id.btn_percent:
                if(tvResult.length() > 0) {
                    long temp = Long.parseLong(tvResult.getText().toString());
                    tvResult.setText(String.valueOf(temp * 0.01));
                }
                break;
        }
        tvInput.setText(inputStr);
    }
}