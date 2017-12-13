package com.example.bwie.mydemo.http;

import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;

import com.example.bwie.mydemo.callback.HomeListCallBack;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;



public class HomeListHttpUtils {
    private static final String TAG = "HttpUtils";
    private static volatile HomeListHttpUtils instance;

    private static Handler handler = new Handler();

    private HomeListHttpUtils() {

    }

    public static HomeListHttpUtils getInstance() {
        if (null == instance) {
            synchronized (HomeListHttpUtils.class) {
                if (instance == null) {
                    instance = new HomeListHttpUtils();
                }
            }
        }
        return instance;
    }



    public void getData(String url, final HomeListCallBack callBack,
                        final Class cls, final String tag) {

        OkHttpClient client = new OkHttpClient();

        final Request request = new Request.Builder()
                .get()
                .url(url.toString())
                .build();
        Call call = client.newCall(request);

        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        // 通过自己传进来的回调接口对象回传回去
                        callBack.onFailed(tag, e);
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String result = response.body().string();
                // 请求成功之后做解析，通过自己的回调接口将数据返回回去
                Log.e(TAG, "onResponse: "+result );

                    handler.post(new Runnable() {
                    @Override
                    public void run() {
                        Object o;
                        if (TextUtils.isEmpty(result)) {
                            o = null;
                        } else {
                            o = GsonUtils.getInstance().fromJson(result, cls);
                        }
                        callBack.onSuccess(tag, o);
                    }
                });
            }
        });


    }


}
