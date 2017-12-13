package com.example.bwie.mydemo.callback;


import com.example.bwie.mydemo.bean.YueBingBean;


public interface YueBinglistCallback {
    void success(YueBingBean body);
    void failed(Exception e);
}
