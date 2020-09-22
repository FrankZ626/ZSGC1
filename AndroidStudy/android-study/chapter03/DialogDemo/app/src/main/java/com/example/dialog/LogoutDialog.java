package com.example.dialog;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

public class LogoutDialog extends DialogFragment {
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("提示")
                .setMessage("确认退出？")
                .setPositiveButton("确认", (dialogInterface, i) -> {
                    dialogInterface.dismiss();
                    LogoutDialog.this.getActivity().finish();
                })
                .setNegativeButton("取消", (dialogInterface, i) -> dialogInterface.dismiss());
        return builder.create();
    }
}
