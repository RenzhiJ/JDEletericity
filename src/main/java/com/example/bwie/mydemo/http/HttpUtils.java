package com.example.bwie.mydemo.http;

import android.os.Handler;
import android.text.TextUtils;

import com.example.bwie.mydemo.callback.CallBack;

import java.io.IOException;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;



public class HttpUtils {
    private static final String TAG = "HttpUtils";
    private static volatile HttpUtils instance;

    private static Handler handler = new Handler();

    private HttpUtils() {

    }

    public static HttpUtils getInstance() {
        if (null == instance) {
            synchronized (HttpUtils.class) {
                if (instance == null) {
                    instance = new HttpUtils();
                }
            }
        }
        return instance;
    }


    /**
     * Get请求
     *
     * @param url
     * @param map
     * @param callBack
     * @param cls
     * @param tag
     */
    public void get(String url, Map<String, String> map, final CallBack callBack,
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
