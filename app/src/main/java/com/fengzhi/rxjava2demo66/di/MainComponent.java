package com.fengzhi.rxjava2demo66.di;

import com.fengzhi.rxjava2demo66.MainActivity;

import javax.inject.Singleton;

import dagger.Component;
@PerActivity
@Component(modules=MainModule.class)
public interface MainComponent {
    void inject(MainActivity mainActivity);
}
