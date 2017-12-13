package com.example.bwie.mydemo.callback;

import com.example.bwie.mydemo.bean.MessageBean;

import java.util.List;


public interface ShopCartsView {
    void success( List<MessageBean.DataBean> data);

    void failed(Exception e);

}
