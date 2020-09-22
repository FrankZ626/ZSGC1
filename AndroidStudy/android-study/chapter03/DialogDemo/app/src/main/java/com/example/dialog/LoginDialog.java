package com.example.dialog;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.util.Objects;

public class LoginDialog extends DialogFragment {
    private LoginListener listener;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.login_dialog, container, false);
        EditText etUsername = view.findViewById(R.id.et_username);
        EditText etPassword = view.findViewById(R.id.et_password);

        Button btnLogin = view.findViewById(R.id.btn_login);
        btnLogin.setOnClickListener(v -> {
            String username = etUsername.getText().toString();
            String password = etPassword.getText().toString();
            listener.onDialogPositiveClick(username, password);
            LoginDialog.this.dismiss();
        });

        Button btnCancel = view.findViewById(R.id.btn_cancel);
        btnCancel.setOnClickListener(v -> {
            listener.onDialogNegativeClick(LoginDialog.this);
            LoginDialog.this.dismiss();
        });
        return view;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        try {
            listener = (LoginListener) context;
        } catch (Exception e) {
            e.printStackTrace();
            throw new ClassCastException(getActivity().toString() + "必须实现LoginListener");
        }
    }

    @Override
    public void onStart() {
        /*
            因为View在添加后,对话框最外层的ViewGroup并不知道我们导入的View所需要的的宽度。
            所以我们需要在onStart生命周期里修改对话框尺寸参数
         */
        WindowManager.LayoutParams params = getDialog().getWindow().getAttributes();
        params.width = ViewGroup.LayoutParams.MATCH_PARENT;
        Objects.requireNonNull(getDialog().getWindow()).setAttributes(params);
        super.onStart();
    }


    public interface LoginListener {
        void onDialogPositiveClick(String username, String password);

        void onDialogNegativeClick(DialogFragment dialog);
    }
}
