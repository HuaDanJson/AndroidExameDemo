package com.ssyw.exam2.util;

import android.app.Application;

public class CCApplication extends Application {

    private static CCApplication app;

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        SharePreferenceUtil.initInstance(getApplicationContext(), SharePreferenceUtil.MODE_ENCRYPT_ALL);
        ToastHelper.init(this);
        DBUserUtils.Init(this);
    }

    public static CCApplication getInstance() {
        return app;
    }

}
