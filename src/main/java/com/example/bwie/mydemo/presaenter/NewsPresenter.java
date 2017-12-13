package com.example.bwie.mydemo.presaenter;

import com.example.bwie.mydemo.bean.Data;
import com.example.bwie.mydemo.bean.DataList;
import com.example.bwie.mydemo.bean.JsonRootBean;
import com.example.bwie.mydemo.callback.CallBack;
import com.example.bwie.mydemo.callback.INewsView;
import com.example.bwie.mydemo.http.HttpUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NewsPresenter {
    private INewsView inv;

    public void attachView(INewsView inv) {
        this.inv = inv;
    }

    public void getNews() {
        Map<String, String> map = new HashMap<>();

        HttpUtils.getInstance().get("http://120.27.23.105/ad/getAd", map, new CallBack() {


            @Override
            public void onSuccess(String tag, Object o) {
                JsonRootBean bean = (JsonRootBean) o;
                List<DataList> list = bean.getMiaosha().getList();
                List<DataList> TuiJianList = bean.getTuijian().getList();
                List<Data> data = bean.getData();
                inv.success(list,TuiJianList,data);
            }

            @Override
            public void onFailed(String tag, Exception e) {
                inv.failed(tag, e);
            }
        }, JsonRootBean.class, "news");
    }


    public void detachView() {
        if (inv != null) {
            inv = null;
        }

    }
}
