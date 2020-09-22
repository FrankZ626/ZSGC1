package com.example.layout;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.layout.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createRelativeLayout();
    }

    private void createRelativeLayout() {
        RelativeLayout relativeLayout = new RelativeLayout(this);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);
        //addRule参数对应RelativeLayout XML布局的属性
        params.addRule(RelativeLayout.CENTER_IN_PARENT);  //设置居中显示
        TextView tvContent = new TextView(this);  //创建TextView控件
        tvContent.setText("Java 代码实现界面布局");  //设置TextView的文字内容
        tvContent.setTextColor(Color.RED);        //设置TextView的文字颜色
        tvContent.setTextSize(18);                //设置TextView的文字大小
        //添加TextView对象和TextView的布局属性
        relativeLayout.addView(tvContent, params);
        setContentView(relativeLayout); //设置在Activity中显示RelativeLayout

    }
}