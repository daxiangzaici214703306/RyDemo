package com.hsns.rydemo.view;

import android.app.Application;
import android.content.Context;

public class RyApplication extends Application {
    private static Context application;

    @Override
    public void onCreate() {
        super.onCreate();
        application = getApplicationContext();
    }

    public static Context getApplication() {
        return application;
    }
}
