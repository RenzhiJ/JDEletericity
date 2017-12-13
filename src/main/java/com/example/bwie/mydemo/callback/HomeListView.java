package com.example.bwie.mydemo.callback;

import com.example.bwie.mydemo.bean.HomeListBean;

import java.util.List;



public interface HomeListView {
    void suc(List<HomeListBean.DataBean> data);

    void fai(String tag, Exception e);

}
