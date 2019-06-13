package com.fengzhi.rxjava2demo66.di;

public class ClothHandler {
    public Clothes handle(Cloth cloth){
        return new Clothes(cloth);
    }
}