package com.fengzhi.rxjava2demo66.rxjava;

import com.fengzhi.rxjava2demo66.net.RetrofitManager;
import com.fengzhi.rxjava2demo66.normal.GankApi;

public class GankRetrofit {
    public static final String URL="http://gank.io/";
    private static final GankApi gankapi=
            RetrofitManager.getInstance().newRetrofit(URL).create(GankApi.class);

    public static GankApi getGankapi() {
        return gankapi;
    }
}
