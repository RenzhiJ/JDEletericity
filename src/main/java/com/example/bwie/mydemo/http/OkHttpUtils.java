package com.example.bwie.mydemo.http;

import android.os.Handler;
import android.util.Log;

import com.example.bwie.mydemo.LoggingInterceptor;
import com.example.bwie.mydemo.callback.MyCallBack;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;



public class OkHttpUtils {
    private static final String TAG = "HttpUtils";
    private static volatile OkHttpUtils instance;
    private static Handler handler = new Handler();


    private OkHttpUtils() {

    }

    public static OkHttpUtils getInstance() {
        if (null == instance) {
            synchronized (OkHttpUtils.class) {
                if (instance == null) {
                    instance = new OkHttpUtils();
                }
            }
        }
        return instance;
    }
    public void post(String url, final MyCallBack callBack, final Class cls) {
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new LoggingInterceptor())
                .build();

        FormBody.Builder builder = new FormBody.Builder();


        FormBody body = builder.build();

        final Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();

        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        callBack.onFailed(e);
                    }
                });

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String result = response.body().string();
                Log.i(TAG, "onResponse: " + result);

                final Object o = GsonUtils.getInstance().fromJson(result, cls);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        callBack.onSuccess(o);
                    }
                });
            }
        });
    }
}
