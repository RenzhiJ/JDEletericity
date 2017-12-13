package com.example.bwie.mydemo.callback;


import com.example.bwie.mydemo.bean.YueBingBean;

import java.util.List;



public interface YueBingView {
    void onSuccess(List<YueBingBean.DataBean> list);
    void onFailed(Exception e);


}
