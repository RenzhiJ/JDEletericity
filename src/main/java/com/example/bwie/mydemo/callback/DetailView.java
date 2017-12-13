package com.example.bwie.mydemo.callback;


import com.example.bwie.mydemo.bean.ShangJiaBean;



public interface DetailView {

    void failed(Exception e);

    void success(ShangJiaBean.DataBean data);
}
