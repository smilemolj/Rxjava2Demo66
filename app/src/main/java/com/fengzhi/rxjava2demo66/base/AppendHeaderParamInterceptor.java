package com.fengzhi.rxjava2demo66.base;

import java.io.IOException;

import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class AppendHeaderParamInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Headers.Builder builder = request.headers().newBuilder();
        Headers newheaders = builder.add("header1", "iam header1").add("token", "iam token").add(
                "uid"
                , "987654321").build();
        Request newRequest = request.newBuilder().headers(newheaders).build();
        return chain.proceed(newRequest);
    }
}
