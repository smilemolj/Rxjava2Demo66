package com.fengzhi.rxjava2demo66.normal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.fengzhi.rxjava2demo66.R;
import com.fengzhi.rxjava2demo66.bean.Androidbean;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NormalActivity extends AppCompatActivity {

    TextView textView;
    private GankApi gankApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_normal);
        textView = findViewById(R.id.tvResult);
        Retrofit retrofit =
                new Retrofit.Builder().baseUrl("http://gank.io").addConverterFactory(GsonConverterFactory.create()).build();
        gankApi = retrofit.create(GankApi.class);
    }

    public void getdata(View view) {
        Call<Androidbean> android = gankApi.getData("Android", "10", "1");
        android.enqueue(new Callback<Androidbean>() {
            @Override
            public void onResponse(Call<Androidbean> call, Response<Androidbean> response) {
                Toast.makeText(NormalActivity.this, "请求成功", Toast.LENGTH_SHORT).show();
                textView.setText(response.body().toString());
            }

            @Override
            public void onFailure(Call<Androidbean> call, Throwable t) {
                Toast.makeText(NormalActivity.this, "请求失败", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void postdata(View view) {
        Call<ResponseBody> postData = gankApi.postData("http://square.github.io/retrofit", "测试",
                "大佬", "android", "true");
        postData.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Toast.makeText(NormalActivity.this, "请求成功", Toast.LENGTH_SHORT).show();
                textView.setText(response.body().toString());
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(NormalActivity.this, "请求失败", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void geturl(View view) {
        Call<Androidbean> android = gankApi.getDataWithUrl("http://gank.io/api/data/Android/10/1");
        android.enqueue(new Callback<Androidbean>() {
            @Override
            public void onResponse(Call<Androidbean> call, Response<Androidbean> response) {
                Toast.makeText(NormalActivity.this, "请求成功", Toast.LENGTH_SHORT).show();
                textView.setText(response.body().toString());
            }

            @Override
            public void onFailure(Call<Androidbean> call, Throwable t) {
                Toast.makeText(NormalActivity.this, "请求失败", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
