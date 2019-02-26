package com.example.test0223.mvp.model.api.service;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;

public interface ApiService {
    @GET("small/commodity/v1/bannerShow")
    Observable<ResponseBody> requestBannerInfo();
    @GET("small/commodity/v1/commodityList")
    Observable<ResponseBody> requestGoodsInfo();
}
