package com.fengzhi.rxjava2demo66.normal;

import com.fengzhi.rxjava2demo66.bean.Androidbean;

import io.reactivex.Completable;
import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Url;

public interface GankApi {
    @GET("api/data/"+"{category}/{size}/{page}")
    Call<Androidbean> getData(@Path("category") String category, @Path("size") String size,
                              @Path("page") String page);

    @GET
    Call<Androidbean> getDataWithUrl(@Url String url);

    @FormUrlEncoded
    @POST("api/add2gank")
    Call<ResponseBody> postData(@Field("url") String url, @Field("desc") String desc, @Field("who"
    ) String who, @Field("type") String type, @Field("debug") String debug);

    @GET("api/data/{category}/{size}/{page}")
    Observable<Androidbean> getDataByRx(@Path("category") String category,
                                        @Path("size") String size,
                                    @Path("page") String page);

    @FormUrlEncoded
    @POST("api/add2gank")
    Observable<Object> postDataByRx(@Field("url") String url, @Field("desc") String desc, @Field(
            "who"
    ) String who, @Field("type") String type, @Field("debug") String debug);

    @FormUrlEncoded
    @POST("api/add2gank")
    Completable postDataByRxNoreturn(@Field("url") String url, @Field("desc") String desc, @Field(
            "who"
    ) String who, @Field("type") String type, @Field("debug") String debug);

}
