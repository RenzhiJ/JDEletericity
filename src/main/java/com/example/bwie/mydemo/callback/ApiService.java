package com.example.bwie.mydemo.callback;

import com.example.bwie.mydemo.bean.SeachBean;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Zhijun on 2017/12/12.
 */

public interface ApiService {
    @GET("product/searchProducts")
    Call<SeachBean> MesageBook(@Query("keywords") String keywords);



}
