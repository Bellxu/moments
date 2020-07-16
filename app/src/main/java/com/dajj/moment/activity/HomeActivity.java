package com.dajj.moment.activity;

import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.dajj.moment.DataGenerator;
import com.dajj.moment.R;
import com.dajj.moment.fragment.BaseFragemnt;
import com.dajj.moment.fragment.HomeFragment;
import com.dajj.moment.net.client.ClientAPI;
import com.dajj.moment.net.client.oauth2.TokenStore;
import com.dajj.moment.net.client.userinfo.UserInfo;
import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import java.io.File;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends BaseActvity {
    private TabLayout mTabLayout;
    private Fragment[] mFragmensts;
    private String login_type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Intent intent = getIntent();
        login_type = intent.getStringExtra("login_type");
        mFragmensts = DataGenerator.getFragments("TabLayout Tab");
        initView();
        request();
    }

    private void request() {
        if (login_type.equals("native")) {
//            TokenStore tokenStore = new TokenStore(getApplicationContext());
//            Call<UserInfo> call = ClientAPI
//                    .userInfo().token(tokenStore.getBearerToken().getAccessToken());
//            call.enqueue(new Callback<UserInfo>() {
//                @Override
//                public void onResponse(Call<UserInfo> call, Response<UserInfo> response) {
//                    UserInfo userInfo = response.body();
//                    BaseFragemnt fragmenst = (BaseFragemnt) mFragmensts[0];
//                    Toast.makeText(HomeActivity.this,userInfo.toString(),Toast.LENGTH_LONG).show();
//                    fragmenst.setData(userInfo);
//                }
//
//                @Override
//                public void onFailure(Call<UserInfo> call, Throwable t) {
//                    Log.e("UserInfoActivity", "Error trying to retrieve user info", t);
//                }
//            });

            String url = "http://192.168.1.7:8080/auth-service/api/user-info";
            TokenStore tokenStore = new TokenStore(getApplicationContext());
            OkGo.<String>get(url).headers("Authorization", "Bearer "+tokenStore.getBearerToken().getAccessToken())
                    .execute(new StringCallback() {
                        @Override
                        public void onSuccess(com.lzy.okgo.model.Response<String> response) {
                            Gson gson = new Gson();
                            UserInfo userInfo = gson.fromJson(response.body().toString(), UserInfo.class);
                            Log.e("test--", response.body().toString());
                            Log.e("test--","userInfo=="+ userInfo.toString());
                            HomeFragment fragmenst = (HomeFragment) mFragmensts[0];
                            Toast.makeText(HomeActivity.this, userInfo.toString(), Toast.LENGTH_LONG).show();
                            fragmenst.setData(userInfo);
                            fragmenst.refresh();

                        }

                        @Override
                        public void onError(com.lzy.okgo.model.Response<String> response) {
                            super.onError(response);
                        }
                    });
        } else {
            TokenStore tokenStore = new TokenStore(getApplicationContext());
            Call<UserInfo> call = ClientAPI
                    .userInfo().token(tokenStore.getToken().getValue());

            call.enqueue(new Callback<UserInfo>() {
                @Override
                public void onResponse(Call<UserInfo> call, Response<UserInfo> response) {
                    UserInfo userInfo = response.body();
                    BaseFragemnt fragmenst = (BaseFragemnt) mFragmensts[0];
                    fragmenst.setData(userInfo);
                }

                @Override
                public void onFailure(Call<UserInfo> call, Throwable t) {
                    Log.e("UserInfoActivity", "Error trying to retrieve user info", t);
                }
            });
        }

    }

    private void initView() {
        mTabLayout = (TabLayout) findViewById(R.id.bottom_tab_layout);

        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                onTabItemSelected(tab.getPosition());

                //改变Tab 状态
//                for(int i=0;i< mTabLayout.getTabCount();i++){
//                    if(i == tab.getPosition()){
//                        mTabLayout.getTabAt(i).setIcon(getResources().getDrawable(DataGenerator.mTabResPressed[i]));
//                    }else{
//                        mTabLayout.getTabAt(i).setIcon(getResources().getDrawable(DataGenerator.mTabRes[i]));
//                    }
//                }

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

//        mTabLayout.addTab(mTabLayout.newTab().setIcon(getResources().getDrawable(R.drawable.tab_recommend_selector)).setText(DataGenerator.mTabTitle[0]));
//        mTabLayout.addTab(mTabLayout.newTab().setIcon(getResources().getDrawable(R.drawable.tab_discover_selector)).setText(DataGenerator.mTabTitle[1]));
//        mTabLayout.addTab(mTabLayout.newTab().setIcon(getResources().getDrawable(R.drawable.tab_message_selector)).setText(DataGenerator.mTabTitle[2]));
//        mTabLayout.addTab(mTabLayout.newTab().setIcon(getResources().getDrawable(R.drawable.tab_mine_selector)).setText(DataGenerator.mTabTitle[3]));

        // 提供自定义的布局添加Tab
        for (int i = 0; i < 4; i++) {
            mTabLayout.addTab(mTabLayout.newTab().setCustomView(DataGenerator.getTabView(this, i)));
        }
    }

    private void onTabItemSelected(int position) {

        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = mFragmensts[0];
                break;
            case 1:
                fragment = mFragmensts[1];
                break;

            case 2:
                fragment = mFragmensts[2];
                break;
            case 3:
                fragment = mFragmensts[3];
                break;
        }
        if (fragment != null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.home_container, fragment).commit();
        }
    }


}