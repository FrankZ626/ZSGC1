package com.example.weixin;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;


public class WechatFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;

    public WechatFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static WechatFragment newInstance(String param1) {
        WechatFragment fragment = new WechatFragment();

        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_weixin, container, false);

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            if (mParam1 != null) {
                initToolbar();
            }
        }

        // 显示选项菜单
        setHasOptionsMenu(true);

        return view;
    }

    private void initToolbar() {
//        // 1. 获取Fragment所在的Activity对象
//        AppCompatActivity activity = (AppCompatActivity) getActivity();
//        if(activity != null) {
//            // 2. 获取Toolbar对象
//            Toolbar toolbar = activity.findViewById(R.id.title_toolbar);
//            // 3. 设置ToolBar
//            activity.setSupportActionBar(toolbar);
//            // 4. 获取ToolBar(原先也成为ActionBar)对象，让工具栏不显示App的标题
//            ActionBar actionBar = activity.getSupportActionBar();
//            if(actionBar != null) {
//                actionBar.setDisplayShowTitleEnabled(false);   // 不显示项目title
//                // actionBar.setDisplayHomeAsUpEnabled(true);  // 显示回退按钮
//            }
//        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_weixin, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_talk:
                Toast.makeText(getActivity(), item.getTitle(), Toast.LENGTH_LONG).show();
        }
        return true;
    }
}
