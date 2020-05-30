package com.project.mobkoosdkdemo;

import android.app.Application;

import com.mobkoo.MobKooHelp;

public class MobKooApplication extends Application {
    @Override
    public void onCreate() {
         super.onCreate();
        MobKooHelp.init(this);
        MobKooHelp.setEnvDebug(true);
    }
}
