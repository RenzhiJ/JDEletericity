package com.example.bwie.mydemo.callback;

import java.io.IOException;


public interface MyCallBack {

    void onFailed(IOException e);

    void onSuccess(Object o);
}
