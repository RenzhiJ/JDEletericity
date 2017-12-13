package com.example.bwie.mydemo.callback;


public interface HomeListCallBack {
    void onSuccess(String tag, Object o);

    void onFailed(String tag, Exception e);
}
