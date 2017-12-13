package com.example.bwie.mydemo.entity;

import com.example.bwie.mydemo.bean.SeachBean;
import com.example.bwie.mydemo.http.RetrofitUtils;
import com.example.bwie.mydemo.presaenter.SeachPresenter;

import retrofit2.Call;

/**
 * Created by Zhijun on 2017/12/12.
 */

public class SearchModel {
    private SeachPresenter presenter;

    public SearchModel(SeachPresenter presenter) {
        this.presenter = presenter;
    }
    public void Seach(String keywords){

        Call<SeachBean> beanCall = RetrofitUtils.getInstance().getApiService().MesageBook(keywords);
        presenter.getData(beanCall);
    }
}
