package com.dajj.moment.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;

import com.dajj.moment.R;
import com.dajj.moment.net.client.oauth2.AccessToken;
import com.dajj.moment.net.client.oauth2.AuthorizationRequest;
import com.dajj.moment.net.client.oauth2.OAuth2StateManager;
import com.dajj.moment.net.client.oauth2.TokenStore;

import java.util.UUID;

public class LoginActivity extends BaseActvity implements View.OnClickListener {

    private Button login;
    private Button web_login;
    private TextView password_login;
    private TextView logon;
    private EditText edit1;
    private EditText edit2;
    private boolean isPasswordLogin = false;
    private TokenStore tokenStore;
    private OAuth2StateManager oauth2StateManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        tokenStore = new TokenStore(this);
        oauth2StateManager = new OAuth2StateManager(LoginActivity.this);
        initView();
    }

    private void initView() {

        login = findViewById(R.id.login);
        password_login = findViewById(R.id.password_login);
        logon = findViewById(R.id.logon);
        edit1 = findViewById(R.id.edit1);
        edit2 = findViewById(R.id.edit2);
        web_login = findViewById(R.id.web_login);
        login.setOnClickListener(this);
        web_login.setOnClickListener(this);
        password_login.setOnClickListener(this);
        logon.setOnClickListener(this);
        edit2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() > 0) {
                    login.setEnabled(true);
                } else {
                    login.setEnabled(false);
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        initDefault();
    }

    private void initDefault() {
        isPasswordLogin = false;
        edit1.setEnabled(false);
        edit1.setText("+86");
        edit2.setHint("请输入手机号");
        login.setText("获取验证码");
        login.setEnabled(false);
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
            case R.id.web_login:
                doWbeLogon();
                break;
            case R.id.password_login:
                ChangeToPasswordLogin();
                break;
        }

    }

    private void doWbeLogon() {

        AccessToken accessToken = tokenStore.getToken();
        if (accessToken != null && !accessToken.isExpired()) {
            Intent intent = new Intent(this, HomeActivity.class);
            startActivity(intent);
            return;
        }

        // create a state parameter to start the authorization flow
        String state = UUID.randomUUID().toString();
        oauth2StateManager.saveState(state);

        // creates the authorization URI to redirect user
        Uri authorizationUri = AuthorizationRequest
                .createAuthorizationUri(state);

        Intent authorizationIntent = new Intent(Intent.ACTION_VIEW);
        authorizationIntent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        authorizationIntent.setData(authorizationUri);
        startActivity(authorizationIntent);
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
