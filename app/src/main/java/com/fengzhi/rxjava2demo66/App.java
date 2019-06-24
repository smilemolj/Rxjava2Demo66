package com.fengzhi.rxjava2demo66;

import android.app.Application;

import com.fengzhi.rxjava2demo66.base.AppendBodyParamsInterceptor;
import com.fengzhi.rxjava2demo66.base.AppendHeaderParamInterceptor;
import com.fengzhi.rxjava2demo66.base.AppendUrlParamInterceptor;
import com.fengzhi.rxjava2demo66.base.NetWorkUtil;
import com.fengzhi.rxjava2demo66.base.NetworkConfig;
import com.fengzhi.rxjava2demo66.base.PreHandleNoNetInterceptor;
import com.fengzhi.rxjava2demo66.normal.GankApi;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class App extends Application {
    public static App instance;
    public static Retrofit retrofitInstance;

    public static App getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public Retrofit getRetrofit() {
        if (retrofitInstance == null) {
            synchronized (App.class) {
                //同步
                if (retrofitInstance == null) {

                    OkHttpClient.Builder builder = new OkHttpClient.Builder();
                    //        okhttp的log信息拦截器
                    if (NetworkConfig.DEBUG) {
                        HttpLoggingInterceptor httpLoggingInterceptor =
                                new HttpLoggingInterceptor();
                        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
                        builder.addInterceptor(httpLoggingInterceptor);
                    }
//                   设置超时
                    builder.connectTimeout(60, TimeUnit.SECONDS);
                    builder.readTimeout(60, TimeUnit.SECONDS);
                    builder.writeTimeout(60, TimeUnit.SECONDS);
//                  错误重连
                    builder.retryOnConnectionFailure(true);
////                  设置缓存(存在post请求的bug)
//                    File cacheFile = new File(getExternalCacheDir(), "retrofit");
//                    Cache cache = new Cache(cacheFile, 1024 * 1024 * 10);
//                    Interceptor cacheInterceptor = new Interceptor() {
//                        @Override
//                        public Response intercept(Chain chain) throws IOException {
//                            Request request = chain.request();
//                            if (!NetWorkUtil.isConnectedByState(instance)) {
////                                取缓存
//                                Request newRequest =
//                                        request.newBuilder().cacheControl(CacheControl.FORCE_CACHE).build();
//                                return chain.proceed(newRequest);
//                            } else {
////                                有网存
//                                int maxTime = 60 * 60 * 24;
//                                Response newResponse =
//                                        chain.proceed(request).newBuilder().header("Cache" +
//                                                "-Control",
//                                                "public," + "only-if-cached," + "max" + "-stale=" + maxTime).removeHeader("Progma").build();
//                                return newResponse;
//                            }
//                        }
//                    };
//                    builder.cache(cache).addInterceptor(cacheInterceptor);
                    retrofitInstance =
                            new Retrofit.Builder().baseUrl("http://gank.io").addConverterFactory(GsonConverterFactory.create()).client(builder.build()).build();
                }
            }
        }
        return retrofitInstance;
    }
}
