package com.example.bwie.mydemo;

/**
*作者：任志军
*编辑时间：2017/12/8
*更新时间：11:03
*用途
 */
import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class LoggingInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request original = chain.request();
        HttpUrl url=original.url().newBuilder()
                .addQueryParameter("source","android")
                .build();
        //添加请求头
        Request request = original.newBuilder()
                .url(url)
                .build();
        return chain.proceed(request);
    }
}