package com.example.cuisine;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class ContentFragment extends Fragment {
    private TextView tvContent;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //将布局文件解析出来
        View view = inflater.inflate(R.layout.fragment_content, container, false);
        if (view != null) {   // 如果view不为空
            tvContent = view.findViewById(R.id.tv_content);
        }
        //获取Activity中的设置文字
        setText(((MainActivity) getActivity()).getSettingText()[0]);
        return view;
    }

    public void setText(String text) {
        tvContent.setText(text);
    }
}
