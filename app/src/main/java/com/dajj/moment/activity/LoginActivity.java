package com.dajj.moment.activity;

import android.os.Bundle;
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
    private boolean isPasswordLogin=false;

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
        isPasswordLogin=true;

    }

    private void doPasswordLogin() {
    }

    private void doLogon() {
        goToLogOn();
    }

    private void goToLogOn() {

    }

    private void doLogin() {
        if (!isPasswordLogin) {
            
        }

    }
}
