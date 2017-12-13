package com.example.bwie.mydemo.presaenter;


import com.example.bwie.mydemo.bean.DataBean;
import com.example.bwie.mydemo.bean.JavasBean;
import com.example.bwie.mydemo.callback.MyCallBack;
import com.example.bwie.mydemo.callback.MyView;
import com.example.bwie.mydemo.http.OkHttpUtils;

import java.io.IOException;
import java.util.List;


public class MyPresenter {
    private MyView inv;

    public void attachView(MyView inv) {
        this.inv = inv;
    }
    public void getData() {

        OkHttpUtils.getInstance().post("http://120.27.23.105/product/getCatagory",  new MyCallBack() {


            @Override
            public void onSuccess( Object o) {
                JavasBean bean = (JavasBean) o;
                if (bean != null) {
                    List<DataBean> data = bean.getData();
                    inv.success( data);
                }
            }

            @Override
            public void onFailed(IOException e) {
                inv.failed(e);
            }
        }, JavasBean.class);
    }


    public void detachView() {
        if (inv != null) {
            inv = null;
        }

    }
}
