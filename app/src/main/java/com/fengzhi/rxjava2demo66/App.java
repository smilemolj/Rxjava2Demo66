package com.fengzhi.rxjava2demo66;

import android.app.Application;

public class App extends Application {
    public static App instance;

    public static App getInstance() {
        return instance;
    }
}
