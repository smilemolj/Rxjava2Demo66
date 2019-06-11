package com.fengzhi.rxjava2demo66.base;

import java.io.IOException;
import java.util.Set;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class AppendBodyParamsInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Set<String> queryKeyName = request.url().queryParameterNames();
        StringBuilder bodyString = new StringBuilder();
        for (String s : queryKeyName) {
            bodyString.append(s)
                    .append("=")
                    .append(request.url().queryParameterValues(s))
                    .append(",");
        }
        RequestBody newBody = RequestBody.create(MediaType.parse("application/json"),
                bodyString.toString().substring(0,bodyString.toString().length()-1));
        Request newRequest = request.newBuilder().post(newBody).build();
        return chain.proceed(newRequest);
    }
}
