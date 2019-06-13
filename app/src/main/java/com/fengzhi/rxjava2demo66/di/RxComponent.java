package com.fengzhi.rxjava2demo66.di;

import com.fengzhi.rxjava2demo66.MainActivity;
import com.fengzhi.rxjava2demo66.rxjava.RxActivity;

import dagger.Component;

@PerActivity
@Component(modules=RxModule.class)
public interface RxComponent {
    void inject(RxActivity rxActivity);
}
