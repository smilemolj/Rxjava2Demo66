package com.fengzhi.rxjava2demo66.base;

import android.app.Application;
import android.content.Context;

import com.fengzhi.rxjava2demo66.App;
import com.fengzhi.rxjava2demo66.rxjava.RxActivity;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;

public class PreHandleNoNetInterceptor implements Interceptor {
    private Context context;

    public PreHandleNoNetInterceptor(Context context) {
        this.context = context;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        if (NetWorkUtil.isConnectedByState(context)){
            return chain.proceed(chain.request());
        }else {
            throw new ResultException(NetworkConfig.ERROR_CODE_NO_NET,"网络问题，请检查网络后重试");
        }
    }
}
