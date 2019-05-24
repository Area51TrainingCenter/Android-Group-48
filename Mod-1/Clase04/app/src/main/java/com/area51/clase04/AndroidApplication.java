package com.area51.clase04;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

public class AndroidApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        Fresco.initialize(this);
    }
}
