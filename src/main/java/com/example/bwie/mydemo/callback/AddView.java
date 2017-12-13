package com.example.bwie.mydemo.callback;


public interface AddView {
    //添加购物车
    void successAdd(String data);
    void failedAdd(Exception e);
}
