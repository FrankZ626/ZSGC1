package com.example.weixin;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;


public class AddressFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;


    public AddressFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static AddressFragment newInstance(String param1) {
        AddressFragment fragment = new AddressFragment();

        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
        }
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // 初始化Toolbar
        if (mParam1 != null) {
            initToolbar();
        }

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_address, container, false);
    }

    private void initToolbar() {
//        AppCompatActivity activity = (AppCompatActivity) getActivity();
//        if(activity != null) {
//            Toolbar toolbar = activity.findViewById(R.id.title_toolbar);
//            activity.setSupportActionBar(toolbar);
//            ActionBar actionBar = activity.getSupportActionBar();
//            if(actionBar != null) {
//                actionBar.setDisplayShowTitleEnabled(false);   // 不显示项目title
//                // actionBar.setDisplayHomeAsUpEnabled(true);  // 显示回退按钮
//            }
//    }
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_address, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_address) {
            Toast.makeText(getActivity(), item.getTitle(), Toast.LENGTH_LONG).show();
        }
        return true;
    }
}
