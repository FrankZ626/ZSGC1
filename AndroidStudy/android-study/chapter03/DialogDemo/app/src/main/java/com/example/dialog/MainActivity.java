package com.example.dialog;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements LoginDialog.LoginListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void click(View view) {
        switch (view.getId()) {
            case R.id.btn_alert_dlg:
                createDlg();
                break;
            case R.id.btn_multi_dlg:
                createMultiBtnDlg();
                break;
            case R.id.btn_list_dlg:
                createListDlg();
                break;
            case R.id.btn_radio_dlg:
                createRadioDlg();
                break;
            case R.id.btn_checkbox_dlg:
                createCheckboxDlg();
                break;
            case R.id.btn_date_dlg:
                createDateDlg();
                break;
            case R.id.btn_time_dlg:
                createTimeDlg();
                break;
            case R.id.btn_custom_dlg:
                createCustomDlg();
                break;
            case R.id.btn_dialog_fragment_logout:
                DialogFragment dialog = new LogoutDialog();
                dialog.show(getSupportFragmentManager(), "退出");
                break;
            case R.id.btn_dialog_fragment_login:
                dialog = new LoginDialog();
                dialog.show(getSupportFragmentManager(), "登录");
                break;
        }
    }

    private void createDlg() {
        new AlertDialog.Builder(this)
                .setIcon(R.mipmap.ic_launcher_round)
                .setTitle("普通对话框")
                .setMessage("是否退出")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        MainActivity.this.finish();
                    }
                })
                .setNegativeButton("退出", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .show();
    }

    private void createMultiBtnDlg() {
        new AlertDialog.Builder(this)
                .setIcon(R.mipmap.ic_launcher_round)
                .setTitle("多按钮对话框")
                .setMessage("喜欢编程么？")
                .setPositiveButton("喜欢", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this, "喜欢", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("不喜欢", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this, "不喜欢", Toast.LENGTH_LONG).show();
                    }
                })
                .setNeutralButton("没感觉", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this, "没感觉", Toast.LENGTH_LONG).show();
                    }
                })
                .show();
    }

    // 用于列表、单选框的数据
    String[] fonts = {"小号", "默认", "中号", "大号", "超大"};
    String yourChoice;      // 保存单选框选择的值
    int currentChoice = 1;  // 保存单选框选择的下标索引

    // 列表对话框
    private void createListDlg() {
        new AlertDialog.Builder(MainActivity.this)
                .setIcon(R.mipmap.ic_launcher_round)
                .setTitle("设置字体大小")
                .setItems(fonts, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        yourChoice = fonts[which];
                        currentChoice = which;
                        Toast.makeText(MainActivity.this, "选中项：" + fonts[which], Toast.LENGTH_LONG).show();
                    }
                })
                .show();
    }

    // 单选对话框
    private void createRadioDlg() {
        new AlertDialog.Builder(MainActivity.this)
                .setIcon(R.mipmap.ic_launcher)
                .setTitle("设置字体大小")
                .setSingleChoiceItems(fonts, currentChoice, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        yourChoice = fonts[which];
                        currentChoice = which;
                    }
                })
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this, "选中项：" + yourChoice, Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .show();
    }

    // 复选对话框
    String[] hobbys = {"旅游", "美食", "看电影", "运动"};
    boolean[] checkedItems = new boolean[]{false, true, false, false};
    private void createCheckboxDlg() {
        new AlertDialog.Builder(MainActivity.this)
                .setIcon(R.mipmap.ic_launcher)
                .setTitle("添加兴趣爱好")
                .setMultiChoiceItems(hobbys, checkedItems, (dialog, which, isChecked) -> {
                        checkedItems[which] = isChecked;
                        Toast.makeText(MainActivity.this, "选中项为：" + hobbys[which], Toast.LENGTH_LONG).show();

                })
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String selectedStr = "选中项：";
                        for (int i = 0; i < checkedItems.length; i++) {
                            if (checkedItems[i]) {
                                selectedStr += hobbys[i] + "  ";
                            }
                        }
                        Toast.makeText(MainActivity.this, selectedStr, Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .show();
    }

    // 日期选择对话框
    String selectedDate = "";  // 存放选择的日期
    private void createDateDlg() {
        int year, month, day;
        // android 8.0支持jdk 8获取日期的方法
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            LocalDate today = LocalDate.now();  // LocalDate获取日期
            year = today.getYear();
            month = today.getMonthValue() - 1; // 月份：0~11
            day = today.getDayOfMonth();
        } else { // jdk 8以下版本获取日期的方法
            Calendar today = Calendar.getInstance();  // 获取当前日历
            year = today.get(Calendar.YEAR);
            month = today.get(Calendar.MONTH);        // 默认的月份是：0~11
            day = today.get(Calendar.DAY_OF_MONTH);
        }
        new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                selectedDate = year + "年" + (month + 1) + "月" + dayOfMonth + "日";
                Toast.makeText(MainActivity.this, "选择的日期是：" + selectedDate, Toast.LENGTH_LONG).show();
            }
        }, year, month, day).show();

    }

    // 时间选择对话框
    String selectedTime = ""; // 存放选择的时间
    private void createTimeDlg() {
        final int hour, minute;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            LocalTime today = LocalTime.now();  // LocalTime获取时间
            hour = today.getHour();
            minute = today.getMinute();
        } else {
            Calendar today = Calendar.getInstance();
            hour = today.get(Calendar.HOUR);
            minute = today.get(Calendar.MINUTE);
        }

        new TimePickerDialog(MainActivity.this, (view, hourOfDay, minute1) -> {
            selectedTime = hourOfDay + ":" + minute1;
            Toast.makeText(MainActivity.this, "选择的时间是：" + selectedTime, Toast.LENGTH_LONG).show();
        }, hour, minute, true).show();
    }

    // 自定义布局对话框
    private void createCustomDlg() {
        // 创建对话框
        final CommonDialog dialog = new CommonDialog(MainActivity.this);
        dialog.setTitle("提示")
                .setMessage("您确定要删除信息？")
                .setPositive("确定")
                .setNegative("取消")
                .setOnClickListener(new CommonDialog.OnClickListener() {
                    @Override
                    public void onPositiveClick() { //确定按钮的点击事件
                        dialog.dismiss();
                    }

                    @Override
                    public void onNegativeClick() { //取消按钮的点击事件
                        dialog.dismiss();
                    }
                })
                .show();

        // 通过Layout的inflater创建布局视图，包括两个参数：layout文件和layout布局文件中的布局id
//        View layout = getLayoutInflater().inflate(R.layout.login_dialog, (ViewGroup) findViewById(R.id.login_dialog));
//        new AlertDialog.Builder(MainActivity.this)
//                .setTitle("用户登录")
//                .setIcon(R.mipmap.ic_launcher)
//                .setView(layout)
//                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        EditText  etUsername = layout.findViewById(R.id.et_username);
//                        EditText  etPassword = layout.findViewById(R.id.et_password);
//                        if ("admin".equals(etUsername.getText().toString()) &&
//                                "123456".equals(etPassword.getText().toString())) {
//                            Toast.makeText(getApplicationContext(), "登录成功", Toast.LENGTH_SHORT).show();
//                        } else {
//                            Toast.makeText(getApplicationContext(), "登录失败", Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                })
//                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                    }
//                })
//                .show();
    }

    @Override
    public void onDialogPositiveClick(String username, String password) {
        if ("admin".equals(username) && "123456".equals(password)) {
            Toast.makeText(getApplicationContext(), "登录成功", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getApplicationContext(), "登录失败", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onDialogNegativeClick(DialogFragment dialog) {

    }

}