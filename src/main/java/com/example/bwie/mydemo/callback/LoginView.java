package com.example.bwie.mydemo.callback;

import com.example.bwie.mydemo.bean.LoginBean;


public interface LoginView {
    void success(LoginBean.DataBean data);

    void failed(Exception e);

}
