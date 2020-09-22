package com.example.weixin;

import android.os.Bundle;
import android.util.SparseArray;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {
    private SparseArray<Fragment> fragments;
    private RadioGroup radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initData();
    }

    private void initData() {
        fragments = new SparseArray<>();
        fragments.put(R.id.btn_weixin, WechatFragment.newInstance(null));
        fragments.put(R.id.btn_address, AddressFragment.newInstance(null));
        fragments.put(R.id.btn_find, FindFragment.newInstance());
        fragments.put(R.id.btn_me, MeFragment.newInstance());
        replaceFragment(fragments.get(R.id.btn_weixin));
    }

    private void initView() {
        radioGroup = findViewById(R.id.btn_group);
        radioGroup.setOnCheckedChangeListener(this);
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.content_layout, fragment);
        transaction.commit();
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        replaceFragment(fragments.get(checkedId));
    }
}