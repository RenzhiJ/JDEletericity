package com.example.bwie.mydemo.presaenter;


import com.example.bwie.mydemo.bean.YueBingBean;
import com.example.bwie.mydemo.callback.Service;
import com.example.bwie.mydemo.callback.YueBinglistCallback;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class YBlistModel {
    private Call<YueBingBean> repos;
    private Service service;
    private static volatile YBlistModel instance;
    private YBlistModel(){

    }

    public static YBlistModel getInstance(){
        if(instance==null){
            synchronized (YBlistModel.class){
                if(null==instance){
                    instance = new YBlistModel();
                }
            }
        }
        return instance;
    }
    public void get( String url,String pscid, int page, int sort, final YueBinglistCallback callback){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        service = retrofit.create(Service.class);
        repos = service.search("getProducts",pscid,page+"",sort+"");


        repos.enqueue(new Callback<YueBingBean>() {
            @Override
            public void onResponse(Call<YueBingBean> call, Response<YueBingBean> response) {
//                Log.e("APP",response.body().getNewslist().toString());

                YueBingBean body = response.body();
                callback.success(body);

            }
            @Override
            public void onFailure(Call<YueBingBean> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}
