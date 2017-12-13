package com.example.bwie.mydemo.presaenter;

import com.example.bwie.mydemo.bean.MessagesBean;
import com.example.bwie.mydemo.callback.RightCallBack;
import com.example.bwie.mydemo.callback.MessagesBeanRightView;
import com.example.bwie.mydemo.http.RightHttpUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class RightPresenter {
    private MessagesBeanRightView iv;

    public void attachView(MessagesBeanRightView iv) {
        this.iv = iv;
    }
    public void getData(String cid) {

        Map<String,String > map =  new HashMap<>();
        map.put("cid",cid);
        RightHttpUtils.getInstance().post("http://120.27.23.105/product/getProductCatagory",map,  new RightCallBack() {


            @Override
            public void onSuccess( Object o) {
                MessagesBean bean = (MessagesBean) o;
                if (bean != null) {
                    List<MessagesBean.DataBean> data = bean.getData();
                    iv.show( data);
                }
            }

            @Override
            public void onFailed(IOException e) {
                iv.failed(e);
            }
        }, MessagesBean.class);
    }


    public void detachView() {
        if (iv != null) {
            iv = null;
        }

    }
}
