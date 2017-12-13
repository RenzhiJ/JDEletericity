package com.example.bwie.mydemo.callback;


import com.example.bwie.mydemo.bean.ShangJiaBean;
import com.example.bwie.mydemo.bean.YueBingBean;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;



public interface Service {
//        http://120.27.23.105/product/getProducts?pscid=1&page=1&sort=0
//        http://120.27.23.105/product/getProductDetail?pid=1
        @POST("{getProducts}")
        Call<YueBingBean> search(@Path("getProducts") String getProducts, @Query("pscid") String key, @Query("page") String num, @Query("sort") String sort);
        @GET("getProductDetail")
        Observable<ShangJiaBean> detailData(@Query("pid") String pid);
}
