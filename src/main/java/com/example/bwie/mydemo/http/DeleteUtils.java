package com.example.bwie.mydemo.http;

import android.os.Handler;

import com.example.bwie.mydemo.bean.DeleteBean;
import com.example.bwie.mydemo.callback.DeleteCallback;
import com.example.bwie.mydemo.interceptor.CustromInterceptor;

import java.io.IOException;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class DeleteUtils {
    private  static  volatile DeleteUtils instance;
    private DeleteUtils(){

    }
    private Handler handler = new Handler();
    public static DeleteUtils getInstance(){
        if(instance==null){
            synchronized (LoginHttpUtils.class){
                if(null==instance){
                    instance= new DeleteUtils();
                }
            }
        }
        return instance;
    }
    public  void post(String url, Map<String,String> map , final DeleteCallback callBack, final Class cls){
        OkHttpClient client = new OkHttpClient
                .Builder()
                .addInterceptor(new CustromInterceptor())
                .build();
        FormBody.Builder builder = new FormBody.Builder();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            builder.add(entry.getKey(), entry.getValue());
        }

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
            public void onResponse(final Call call, Response response) throws IOException {
                final String data = response.body().string().trim();
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        Object o = GsonUtils.getInstance().fromJson(data, DeleteBean.class);
                        callBack.onSuccess(o);

                    }
                });
            }
        });
    }
}
