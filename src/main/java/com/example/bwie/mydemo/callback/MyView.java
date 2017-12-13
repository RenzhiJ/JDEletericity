package com.example.bwie.mydemo.callback;


import com.example.bwie.mydemo.bean.DataBean;

import java.util.List;


public interface MyView {
    void success(List<DataBean> news);

    void failed(Exception e);


}
