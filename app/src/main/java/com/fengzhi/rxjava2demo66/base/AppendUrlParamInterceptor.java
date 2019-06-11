package com.fengzhi.rxjava2demo66.base;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class AppendUrlParamInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
//        拿到拥有以前的request里的 url的 那些信息的builder
        HttpUrl.Builder builder = request.url().newBuilder();
//          得到新的url已经追加好了，
        HttpUrl newUrl = builder.addQueryParameter("deviceId", "123456").addQueryParameter("token"
                , "iamtoken").addQueryParameter("appver", "1.0.0-beat").build();

//        用新的rul构建新的request，发送给服务器，
        Request newRequest = request.newBuilder().url(newUrl).build();
        return chain.proceed(newRequest);
    }
}
