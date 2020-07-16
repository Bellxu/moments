package com.dajj.moment.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.dajj.moment.R;
import com.dajj.moment.net.client.userinfo.UserInfo;

public class HomeFragment extends BaseFragemnt{

    private static HomeFragment homeFragment;
    private TextView user_name;
    private TextView user_mail;
    private UserInfo userInfo;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragemt_home, container, false);
        user_name = view.findViewById(R.id.user_name);
        user_mail = view.findViewById(R.id.user_mail);
        return view;
    }



    public static BaseFragemnt newInstance() {
        if (homeFragment==null){
            homeFragment = new HomeFragment();
        }
        return homeFragment;
    }

    @Override
    public void refresh() {
        user_name.setText(userInfo.getUsername());
        user_mail.setText(userInfo.getEmail());
    }

    @Override
    public <E> void setData(E e) {
        if (e instanceof UserInfo) {
            this.userInfo=(UserInfo)e;
        }
    }
}
