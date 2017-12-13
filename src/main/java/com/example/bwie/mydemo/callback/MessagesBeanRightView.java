package com.example.bwie.mydemo.callback;

import com.example.bwie.mydemo.bean.MessagesBean;

import java.util.List;


public interface MessagesBeanRightView {
    void show(List<MessagesBean.DataBean> news);

    void failed(Exception e);
}
