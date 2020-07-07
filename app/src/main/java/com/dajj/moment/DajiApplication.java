package com.dajj.moment;

import android.app.Application;

import androidx.multidex.MultiDex;

import com.lzy.okgo.OkGo;

public class DajiApplication extends Application {


    @Override
    public void onCreate() {
        super.onCreate();
        OkGo.getInstance().init(this);
        MultiDex.install(this);


    }
}
