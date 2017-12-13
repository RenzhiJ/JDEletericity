package com.example.bwie.mydemo.callback;


public interface HomeBottomCallBack {
    void onSuccess(String tag, Object o);

    void onFailed(String tag, Exception e);
}
