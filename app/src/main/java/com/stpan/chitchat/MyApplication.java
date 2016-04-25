package com.stpan.chitchat;

import android.app.Application;
import android.content.SharedPreferences;
import android.content.pm.InstrumentationInfo;

/**
 * 功能：
 * 创建时间:2016/4/4 22:29
 * 作者:pst
 */
public class MyApplication extends Application {
    private static MyApplication instance = null;
    private SharedPreferences sharedPreferences = null;

    private String USER_ID = "userId";
    public MyApplication() {}

    public static MyApplication getInstance(){
        return instance;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        init();
    }

    private void init(){
        instance = this;
        sharedPreferences = getSharedPreferences("chitchat",MODE_PRIVATE);
    }

    public String getUserId() {
        return sharedPreferences.getString(USER_ID,null);
    }

    public void setUserId(String userId) {
        sharedPreferences.edit().putString(USER_ID,userId);
    }
}
