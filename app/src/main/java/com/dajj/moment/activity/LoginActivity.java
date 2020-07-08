package com.dajj.moment.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;

import com.dajj.moment.R;

public class LoginActivity extends BaseActvity implements View.OnClickListener {

    private Button login;
    private TextView password_login;
    private TextView logon;
    private EditText edit1;
    private EditText edit2;
    private boolean isPasswordLogin = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
    }

    private void initView() {

        login = findViewById(R.id.login);
        password_login = findViewById(R.id.password_login);
        logon = findViewById(R.id.logon);
        edit1 = findViewById(R.id.edit1);
        edit2 = findViewById(R.id.edit2);
        login.setOnClickListener(this);
        password_login.setOnClickListener(this);
        logon.setOnClickListener(this);
        initDefault();
    }

    private void initDefault() {
        isPasswordLogin = false;
        edit1.setEnabled(false);
        edit1.setText("+86");
        edit2.setHint("请输入手机号");
        login.setText("获取验证码");
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.login:
                doLogin();
                break;
            case R.id.logon:
                doLogon();
                break;
            case R.id.password_login:
                ChangeToPasswordLogin();
                break;
        }

    }

    private void ChangeToPasswordLogin() {
        if (isPasswordLogin) {
            isPasswordLogin = false;
            edit1.setEnabled(false);
            edit1.setText("+86");
            edit2.setHint(R.string.input_phone_number_tip);
            login.setText(R.string.get_verify_code);
        } else {
            isPasswordLogin = true;
            edit1.setEnabled(true);
            edit1.setHint("账号/手机/邮箱");
            if (!TextUtils.isEmpty(edit2.getText())) {
                edit1.setText(edit2.getText());
            } else {
                edit1.setText("");
            }
            edit2.setHint(R.string.input_password);
            edit2.setTransformationMethod(PasswordTransformationMethod.getInstance());
            login.setText(R.string.login);
        }


    }

    private void doPasswordLogin() {
    }

    private void doLogon() {
        goToLogOn();
    }

    //注册
    private void goToLogOn() {

    }

    // 登陆
    private void doLogin() {
        if (!isPasswordLogin) {
            String phoneNumber = edit2.getText().toString();
        }

    }
}
