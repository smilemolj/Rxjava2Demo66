package com.fengzhi.rxjava2demo66;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.fengzhi.rxjava2demo66.di.Cloth;
import com.fengzhi.rxjava2demo66.di.ClothHandler;
import com.fengzhi.rxjava2demo66.di.Clothes;
import com.fengzhi.rxjava2demo66.di.DaggerMainComponent;
import com.fengzhi.rxjava2demo66.di.MainComponent;
import com.fengzhi.rxjava2demo66.di.MainModule;
import com.fengzhi.rxjava2demo66.di.Shoe;
import com.fengzhi.rxjava2demo66.normal.NormalActivity;
import com.fengzhi.rxjava2demo66.rxjava.RxActivity;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

public class MainActivity extends AppCompatActivity {

//    @Inject
//    Cloth cloth;
//    @Inject
//    Shoe shoe;
//    @Inject
//    Clothes clothes;
    //    @Inject
//    @Named("red")
//    Cloth redCloth;
//    @Inject
//    @Named("blue")
//    Cloth blueCloth;
    @Inject
    Cloth redCloth;
    @Inject
    ClothHandler clothHandler;
//    @Inject
//    Cloth blueCloth;
    private TextView textViewdi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textViewdi = findViewById(R.id.textView);
        MainComponent build = DaggerMainComponent.builder().mainModule(new MainModule()).build();
        build.inject(this);
//        textViewdi.setText("我现在有" + cloth+"和"+shoe+"和"+clothes);
//        textViewdi.setText("我现在有" + redCloth + "和" + blueCloth);
//        textViewdi.setText("redCloth=clothes中的cloth吗?:" + (redCloth == clothes.getCloth()));
        textViewdi.setText("红布料加工后变成了" + clothHandler.handle(redCloth) + "\nclothHandler地址:" + clothHandler);
//        Observable.interval(3, TimeUnit.SECONDS).just(1,2,3,4,5).subscribe(new
//        Consumer<Integer>() {
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
