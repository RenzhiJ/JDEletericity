package com.example.bwie.mydemo.presaenter;

import com.example.bwie.mydemo.bean.SeachBean;
import com.example.bwie.mydemo.callback.Seach;
import com.example.bwie.mydemo.callback.SeachPre;
import com.example.bwie.mydemo.entity.SearchModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Zhijun on 2017/12/12.
 */

public class SeachPresenter implements SeachPre{
    private Seach seach;

    public void setSeach(Seach seach) {
        this.seach = seach;
    }

    public void getData(Call<SeachBean> beanCall){
        beanCall.enqueue(new Callback<SeachBean>() {
            @Override
            public void onResponse(Call<SeachBean> call, Response<SeachBean> response) {
                if(response!=null){
                    SeachBean body = response.body();
                    if(body!=null){
                        seach.onSuccess(body);
                    }
                }

            }

            @Override
            public void onFailure(Call<SeachBean> call, Throwable t) {

            }
        });
    }

    @Override
    public void getData(String keywords) {
        SearchModel model = new SearchModel(this);
        model.Seach(keywords);
    }
}
