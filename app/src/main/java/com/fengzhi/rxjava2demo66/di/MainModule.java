package com.fengzhi.rxjava2demo66.di;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class MainModule {

    //    @Provides
//    public Cloth getCloth() {
//        Cloth cloth = new Cloth();
//        cloth.setColor("红色");
//        return cloth;
//    }
//    @Provides
////    public Clothes getClothes(Cloth cloth) {
////        return new Clothes(cloth);
////    }

    //    @Singleton
//    @Provides
////    @Named("red")
//    public Cloth getRedCloth() {
//        Cloth cloth = new Cloth();
//        cloth.setColor("红色");
//        return cloth;
//    }
//    @Provides
//    @Named("blue")
//    public Cloth getBlueCloth() {
//        Cloth cloth = new Cloth();
//        cloth.setColor("蓝色");
//        return cloth;
//    }
    @PerActivity
    @Provides
    public Cloth getRedCloth() {
        Cloth cloth = new Cloth();
        cloth.setColor("红色");
        return cloth;
    }

    @PerActivity
    @Provides
    public ClothHandler getClothHandler() {
        return new ClothHandler();
    }

}
