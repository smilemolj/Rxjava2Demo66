package com.fengzhi.rxjava2demo66.rxjava;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.fengzhi.rxjava2demo66.App;
import com.fengzhi.rxjava2demo66.base.AppendBodyParamsInterceptor;
import com.fengzhi.rxjava2demo66.base.AppendHeaderParamInterceptor;
import com.fengzhi.rxjava2demo66.base.AppendUrlParamInterceptor;
import com.fengzhi.rxjava2demo66.base.NetWorkUtil;
import com.fengzhi.rxjava2demo66.base.NetworkConfig;
import com.fengzhi.rxjava2demo66.R;
import com.fengzhi.rxjava2demo66.base.PreHandleNoNetInterceptor;
import com.fengzhi.rxjava2demo66.bean.Androidbean;
import com.fengzhi.rxjava2demo66.di.Cloth;
import com.fengzhi.rxjava2demo66.di.ClothHandler;
import com.fengzhi.rxjava2demo66.di.DaggerRxComponent;
import com.fengzhi.rxjava2demo66.di.RxComponent;
import com.fengzhi.rxjava2demo66.di.RxModule;
import com.fengzhi.rxjava2demo66.net.RetrofitManager;
import com.fengzhi.rxjava2demo66.normal.GankApi;
import com.fengzhi.rxjava2demo66.normal.NormalActivity;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RxActivity extends AppCompatActivity {

    TextView textView;
    TextView textviewdi;
    private GankApi gankApi1;
    private GankApi gankApi;
    @Inject
    Cloth blueCloth;
    @Inject
    ClothHandler clothHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx);
        RetrofitManager.init(this);
        textView = findViewById(R.id.tvResult);
        textviewdi = findViewById(R.id.textviewdi);
        RxComponent build = DaggerRxComponent.builder().rxModule(new RxModule()).build();
        build.inject(this);
        textviewdi.setText("蓝布料加工后变成了" + clothHandler.handle(blueCloth) + "\nclothHandler地址:" + clothHandler);
//        OkHttpClient.Builder builder = new OkHttpClient.Builder();
//        自动追加参数
//        builder.addInterceptor(new PreHandleNoNetInterceptor(this));//按照顺序执行拦截器
//        builder.addInterceptor(new AppendUrlParamInterceptor());
//        builder.addInterceptor(new AppendHeaderParamInterceptor());
//        builder.addInterceptor(new AppendBodyParamsInterceptor());



        //设置超时
//        builder.connectTimeout(60, TimeUnit.SECONDS);
//        builder.readTimeout(60, TimeUnit.SECONDS);
//        builder.writeTimeout(60, TimeUnit.SECONDS);
//        错误重连
//        builder.retryOnConnectionFailure(true);

//        okhttp的log信息拦截器
//        if (NetworkConfig.DEBUG) {
//            HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
//            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
//            builder.addInterceptor(httpLoggingInterceptor);
//        }


        gankApi = GankRetrofit.getGankapi();
        gankApi1=App.getInstance().getRetrofit().create(GankApi.class);
    }

    public void getdata(View view) {
        Observable<Androidbean> observable = gankApi.getDataByRx("Android", "10", "1");
        observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<Androidbean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Androidbean androidbean) {
                textView.setText(androidbean.toString());
            }

            @Override
            public void onError(Throwable e) {
                Toast.makeText(RxActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onComplete() {

            }
        });
    }

    public void geturl(View view) {
        Completable completable = gankApi.postDataByRxNoreturn("http://square.github.io/retrofit"
                , "测试", "大佬", "Android", "true");
        completable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new CompletableObserver() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onComplete() {
                Toast.makeText(RxActivity.this, "请求成功", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(Throwable e) {
                Toast.makeText(RxActivity.this, "请求失败" + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void postdata(View view) {
        Observable<Object> observable = gankApi.postDataByRx("http://square.github.io/retrofit",
                "测试", "大佬", "Android", "true");
        observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object s) throws Exception {
                textView.setText(s.toString());
            }
        });
    }

    public void btnRemoveWrapper(View view) {

    }

    public void postdata1(View view) {
        gankApi1.postData("http://square.github.io/retrofit", "测试", "大佬", "android", "true").enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Toast.makeText(RxActivity.this, "请求成功", Toast.LENGTH_SHORT).show();
                textView.setText(response.body().toString());
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(RxActivity.this, "请求失败", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
