package com.example.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CommonDialog extends AlertDialog {
    private TextView tvTitle;              //显示的标题
    private TextView tvMessage;            //显示的消息
    private Button btnNegative, btnPositive;  //确认和取消按钮

    public CommonDialog(Context context) {
        super(context);
    }

    private String message;
    private String title;
    private String positive, negative;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_dialog);
        initView();     //初始化界面控件
        initEvent();    //初始化界面控件的点击事件
    }

    //初始化界面控件
    private void initView() {
        btnNegative = findViewById(R.id.negative);
        btnPositive = findViewById(R.id.positive);
        tvTitle = findViewById(R.id.title);
        tvMessage = findViewById(R.id.message);
    }

    //初始化界面控件的显示数据
    private void refreshView() {
        //如果自定义了title和message会 显示自定义的信息，否则不显示title和message的信息
        if (!TextUtils.isEmpty(title)) {
            tvTitle.setText(title);                 //设置标题控件的文本为自定义的title
            tvTitle.setVisibility(View.VISIBLE);    //标题控件设置为显示状态
        } else {
            tvTitle.setVisibility(View.GONE);       //标题控件设置为隐藏状态
        }
        if (!TextUtils.isEmpty(message)) {
            tvMessage.setText(message);             //设置消息控件的文本为自定义的message信息
        }
        //如果自定义了按钮的文本，则按钮显示自定义的文本，否则，按钮显示“确定”或“取消”文本
        if (!TextUtils.isEmpty(positive)) {
            btnPositive.setText(positive);          //设置按钮的文本为自定义的文本信息
        } else {
            btnPositive.setText("确定");             //设置按钮文本为“确定”
        }
        if (!TextUtils.isEmpty(negative)) {
            btnNegative.setText(negative);
        } else {
            btnNegative.setText("取消");
        }
    }

    //初始化界面的确定和取消监听器
    private void initEvent() {
        //设置确定按钮的点击事件的监听器
        btnPositive.setOnClickListener((v) -> {
            if (onClickListener != null) {
                onClickListener.onPositiveClick();
            }
        });
        //设置取消按钮的点击事件的监听器
        btnNegative.setOnClickListener((v) -> {
            if (onClickListener != null) {
                onClickListener.onNegativeClick();
            }
        });
    }

    @Override
    public void show() {
        super.show();
        refreshView();
    }

    public interface OnClickListener {
        void onPositiveClick();//实现确定按钮点击事件的方法

        void onNegativeClick(); //实现取消按钮点击事件的方法
    }

    //设置确定取消按钮的回调
    public OnClickListener onClickListener;

    public CommonDialog setOnClickListener(OnClickListener
                                                   onClickListener) {
        this.onClickListener = onClickListener;
        return this;
    }

    public CommonDialog setMessage(String message) {
        this.message = message;
        return this;
    }

    public CommonDialog setTitle(String title) {
        this.title = title;
        return this;
    }

    public CommonDialog setPositive(String positive) {
        this.positive = positive;
        return this;
    }

    public CommonDialog setNegative(String negative) {
        this.negative = negative;
        return this;
    }
}
