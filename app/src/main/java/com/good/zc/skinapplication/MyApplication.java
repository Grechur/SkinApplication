package com.good.zc.skinapplication;

import android.app.Application;

import com.good.zc.skinplugin.skin.SkinManager;

/**
 * Created by Zc on 2018/4/26.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        SkinManager.getInstance().init(this);
    }
}
