package com.fengzhi.rxjava2demo66.net;

import android.content.Context;

import com.fengzhi.rxjava2demo66.base.NetWorkUtil;
import com.fengzhi.rxjava2demo66.rxjava.RxActivity;

import java.io.File;
import java.io.IOException;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitManager {
    private static Context mcontext;
    private OkHttpClient okHttpClient=new OkHttpClient();//    这句代码随便写的，，，
    private static class InnerHolder{
        private static RetrofitManager INSTANCE =new RetrofitManager();
    }
    public static void init(Context context){
        mcontext=context.getApplicationContext();
    }
    public static RetrofitManager getInstance(){
        return InnerHolder.INSTANCE;
    }

    public Retrofit newRetrofit(String url){
        return new Retrofit.Builder().baseUrl("http://gank.io").addConverterFactory(GsonConverterFactory.create()).addCallAdapterFactory(RxJava2CallAdapterFactory.create()).client(okHttpClient).build();
    }

    private RetrofitManager() {
//        在构造方法里是为了得到一个单例的okhttpclient实例
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
//        自动追加参数
//        builder.addInterceptor(new PreHandleNoNetInterceptor(this));//按照顺序执行拦截器
//        builder.addInterceptor(new AppendUrlParamInterceptor());
//        builder.addInterceptor(new AppendHeaderParamInterceptor());
//        builder.addInterceptor(new AppendBodyParamsInterceptor());
        File cacheFile = new File(mcontext.getExternalCacheDir(), "csdn retrofit");
        Cache cache = new Cache(cacheFile, 1024 * 1024 * 10);
        Interceptor cacheInterceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                if (!NetWorkUtil.isConnectedByState(mcontext)) {
//                    取缓存
                    Request newRequest =
                            request.newBuilder().cacheControl(CacheControl.FORCE_CACHE).build();
                    return chain.proceed(newRequest);
                } else {
//                    有网存数据
                    int maxTime = 60 * 60 * 24;
                    Response response = chain.proceed(request);
//                    套路代码"http cache"
                    Response newResponse = response.newBuilder().header("Cache-Control", "public," +
                            "only-if-cached," + "max-stale=" + maxTime).removeHeader("Progma").build();
                    return newResponse;
                }
            }
        };
        builder.cache(cache).addInterceptor(cacheInterceptor);
    }
}
