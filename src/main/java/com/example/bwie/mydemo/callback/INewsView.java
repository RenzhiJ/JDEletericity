package com.example.bwie.mydemo.callback;

import com.example.bwie.mydemo.bean.Data;
import com.example.bwie.mydemo.bean.DataList;

import java.util.List;



public interface INewsView {
    void success(List<DataList> list, List<DataList> data, List<Data> datas);

    void failed(String tag, Exception e);

//    void success(List<DataList> list);
}
