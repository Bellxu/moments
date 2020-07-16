package com.dajj.moment.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.PasswordTransformationMethod;
import android.text.method.SingleLineTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.dajj.moment.R;
import com.dajj.moment.net.client.ClientAPI;
import com.dajj.moment.net.client.login.BearerToken;
import com.dajj.moment.net.client.login.UserLogin;
import com.dajj.moment.net.client.oauth2.AccessToken;
import com.dajj.moment.net.client.oauth2.AuthorizationRequest;
import com.dajj.moment.net.client.oauth2.OAuth2StateManager;
import com.dajj.moment.net.client.oauth2.TokenStore;
import java.util.UUID;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
            edit2.setTransformationMethod(SingleLineTransformationMethod.getInstance());
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
            edit2.setText("");
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

    private UserLogin getLoginInfo() {
        UserLogin userLogin = new UserLogin();
        userLogin.setPrincipal(edit1.getText().toString());
        userLogin.setCredentials(edit2.getText().toString());
        userLogin.setType("email");
        return userLogin;
    }

    // 登陆
    private void doLogin() {
        if (!isPasswordLogin) {
            String phoneNumber = edit2.getText().toString();
        }
        gotoLogin();
    }

    private void gotoLogin() {
        {
            UserLogin loginInfo = getLoginInfo();
            Call<BearerToken> call = ClientAPI.login().requestToken(loginInfo);

            call.enqueue(new Callback<BearerToken>() {
                @Override
                public void onResponse(Call<BearerToken> call, Response<BearerToken> response) {
                    if (response.code()==200) {
                        BearerToken token = response.body();
                        Log.v("test--",token.toString()+"code=="+response.code());
                        TokenStore tokenStore = new TokenStore(LoginActivity.this);
                        tokenStore.save(token);
//                        Toast.makeText(LoginActivity.this, token.toString()+"code=="+response.code(), Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(LoginActivity.this,
                                HomeActivity.class);
                        intent.putExtra("login_type","native");
                        startActivity(intent);
                        finish();
                    }else {
                        Toast.makeText(LoginActivity.this, "code=="+response.code(), Toast.LENGTH_SHORT).show();
                        Log.v("test--","code=="+response.code());
                    }

                }

                @Override
                public void onFailure(Call<BearerToken> call, Throwable t) {
                    Log.v("test--","onFailure" );
                    Toast.makeText(LoginActivity.this, "onFailure t=="+t.toString(), Toast.LENGTH_SHORT).show();

                }
            });
        }

    }


}
