package com.example.bwie.mydemo.presaenter;

import com.example.bwie.mydemo.LoggingInterceptor;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class OkHttpModel {
    private static volatile OkHttpModel instance;
    private Retrofit retrofit;

    private OkHttpModel(){

    }
    public static OkHttpModel getInstance(String baseUrl){
        if (instance==null){
            synchronized (OkHttpModel.class){
                if (null==instance){
                    instance = new OkHttpModel(baseUrl);

                }
            }
        }
        return instance;

    }
    private OkHttpModel(String baseUrl){
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new LoggingInterceptor()).build();
        retrofit = new Retrofit.Builder()
                .client(client)

                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(baseUrl)
                .build();
    }

    public static OkHttpModel getInstance(){
        if (null == instance){
            return  getInstance("http://api.tianapi.com/");
        }
        return instance;
    }
    public Retrofit getRetrofit(){
        return retrofit;
    }
}
