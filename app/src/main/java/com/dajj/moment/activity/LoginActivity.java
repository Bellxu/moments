package com.dajj.moment.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;

import com.dajj.moment.R;

public class LoginActivity extends BaseActvity implements View.OnClickListener {

    private Button logon_button;
    private Button login_button;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
    }

    private void initView() {
        login_button = findViewById(R.id.login);
        logon_button = findViewById(R.id.logon);
        login_button.setOnClickListener(this);
        logon_button.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.login:
                break;
            case R.id.logon:
                break;
        }

    }
}
