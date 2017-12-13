package com.example.bwie.mydemo.http;

import com.example.bwie.mydemo.LoggingInterceptor;
import com.example.bwie.mydemo.callback.ApiService;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Zhijun on 2017/12/12.
 */

public class RetrofitUtils {
    private static volatile RetrofitUtils instance;
    private final ApiService apiService;
    private OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new LoggingInterceptor()).build();

    public static RetrofitUtils getInstance(){
        if (instance==null){
            synchronized (RetrofitUtils.class){
                if (instance==null){
                    instance=new RetrofitUtils();
                }
            }
        }
        return instance;
    }
    private RetrofitUtils (){
        Retrofit retrofit = new Retrofit.Builder().addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .baseUrl("http://120.27.23.105/")
                .build();
        apiService = retrofit.create(ApiService.class);

    }

    public ApiService getApiService(){
        return apiService;
    }
}
