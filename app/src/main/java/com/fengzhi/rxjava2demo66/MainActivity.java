package com.fengzhi.rxjava2demo66;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.fengzhi.rxjava2demo66.normal.NormalActivity;
import com.fengzhi.rxjava2demo66.rxjava.RxActivity;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        Observable.interval(3, TimeUnit.SECONDS).just(1,2,3,4,5).subscribe(new Consumer<Integer>() {
//            @Override
//            public void accept(Integer integer) throws Exception {
//                Toast.makeText(MainActivity.this, "啦啦啦"+integer, Toast.LENGTH_SHORT).show();
//                Log.i("tag","啦啦啦"+integer);
//            }
//        });
    }

    public void test(View view) {
        startActivity(new Intent(MainActivity.this, NormalActivity.class));
    }

    public void rxtest(View view) {
        startActivity(new Intent(MainActivity.this, RxActivity.class));
    }
}
