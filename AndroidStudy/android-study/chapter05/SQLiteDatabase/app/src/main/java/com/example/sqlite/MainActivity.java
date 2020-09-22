package com.example.sqlite;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText etName;
    private EditText etPhone;
    private RecyclerView rvAddress;
    private AddressAdapter adapter;
    private int position;

    private List<Address> addresses;
    private AddressDao addressDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initData();
        initView();
    }

    private void initData() {
        addressDao = new AddressDao(this);
        addresses = new ArrayList<>();
//        addresses = addressDao.query();
        position = 0;
    }

    private void initView() {
        etName = findViewById(R.id.et_name);
        etPhone = findViewById(R.id.et_phone);

        Button btnAdd = findViewById(R.id.btn_add);
        Button btnUpdate = findViewById(R.id.btn_update);
        Button btnDelete = findViewById(R.id.btn_delete);
        Button btnQuery = findViewById(R.id.btn_query);

        btnAdd.setOnClickListener(this);
        btnUpdate.setOnClickListener(this);
        btnDelete.setOnClickListener(this);
        btnQuery.setOnClickListener(this);

        rvAddress = findViewById(R.id.rv_address);
        rvAddress.setLayoutManager(new LinearLayoutManager(this));
        rvAddress.addItemDecoration(new DividerItemDecoration(this, LinearLayout.VERTICAL));
        adapter = new AddressAdapter(addresses);
        rvAddress.setAdapter(adapter);
        adapter.setOnItemClickListener(new AddressAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                MainActivity.this.position = position;
                etName.setText(addresses.get(position).getName());
                etPhone.setText(addresses.get(position).getPhone());

                adapter.setCurrentPos(position);
                adapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_add:
                insert();
                break;
            case R.id.btn_update:
                update();
                break;
            case R.id.btn_delete:
                delete();
                break;
            case R.id.btn_query:
                query();
                break;
        }
        // 关闭软键盘
        InputMethodManager imm = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.hideSoftInputFromWindow(this.getWindow().getDecorView().getWindowToken(), 0);
        }
    }

    private void query() {
        addresses.clear();
        addresses.addAll(addressDao.query());
        adapter.notifyDataSetChanged();
    }

    private void delete() {
        new AlertDialog.Builder(this).setTitle("删除").setMessage("确认删除？")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int pos = MainActivity.this.position;
                        if (addressDao.delete(addresses.get(pos).get_id())) {
                            addresses.remove(pos);
                            adapter.notifyItemRemoved(pos);
                            dialog.dismiss();
                            Toast.makeText(MainActivity.this, "删除成功", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(MainActivity.this, "删除失败", Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).show();

    }

    private void insert() {
        boolean flag = true;

        String name = etName.getText().toString();
        String phone = etPhone.getText().toString();
        if (TextUtils.isEmpty(name)) {
            Toast.makeText(this, "请输入用户名", Toast.LENGTH_SHORT).show();
            flag = false;
        } else if (TextUtils.isEmpty(phone)) {
            Toast.makeText(this, "请输入手机号", Toast.LENGTH_SHORT).show();
            flag = false;
        } else if (!phone.matches("1[3-9]\\d{9}")) {
            Toast.makeText(this, "手机号不正确", Toast.LENGTH_SHORT).show();
            flag = false;
        }
        if (flag) {
            Address address = new Address();
            address.setName(name);
            address.setPhone(phone);
            int id = addressDao.insert(address);
            if ( id > 0) {
                address.set_id(id);
                addresses.add(address);
                adapter.notifyItemInserted(addresses.size() - 1);
                Toast.makeText(this, "添加成功", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "添加失败", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void update() {
        boolean flag = true;

        String name = etName.getText().toString();
        String phone = etPhone.getText().toString();
        if (TextUtils.isEmpty(name)) {
            Toast.makeText(this, "请输入用户名", Toast.LENGTH_SHORT).show();
            flag = false;
        }
        if (TextUtils.isEmpty(phone)) {
            Toast.makeText(this, "请输入手机号", Toast.LENGTH_SHORT).show();
            flag = false;
        } else if (!phone.matches("1[3-9]\\d{9}")) {
            Toast.makeText(this, "手机号不正确", Toast.LENGTH_SHORT).show();
            flag = false;
        }
        if (flag) {
            Address address = addresses.get(position);
            address.setName(name);
            address.setPhone(phone);
            if (addressDao.update(address)) {
                adapter.notifyItemChanged(position);
                Toast.makeText(this, "修改成功", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "修改失败", Toast.LENGTH_SHORT).show();
            }
        }

    }
}