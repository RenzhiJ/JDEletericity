package com.example.bwie.mydemo.callback;

public interface CallBack {
    //返回值
    void onSuccess(String tag, Object o);

    void onFailed(String tag, Exception e);
}
