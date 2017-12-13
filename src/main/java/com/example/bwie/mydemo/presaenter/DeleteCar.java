package com.example.bwie.mydemo.presaenter;


import android.util.Log;

import com.example.bwie.mydemo.bean.DeleteBean;
import com.example.bwie.mydemo.callback.DeleteCallback;
import com.example.bwie.mydemo.callback.DeleteView;
import com.example.bwie.mydemo.http.DeleteUtils;

import java.util.HashMap;
import java.util.Map;

import static android.content.ContentValues.TAG;



public class DeleteCar {
    private DeleteView iv;

    public void attach(DeleteView iv) {
        this.iv = iv;
    }

    public void getDelete(String uid, String pid) {
//        https:
//www.zhaoapi.cn/product/addCart?uid=71&pid=71
        //给map集合添加数据
        Map<String, String> map = new HashMap<>();
        map.put("uid",uid);
        map.put("pid",pid);
        //调用http://120.27.23.105/product/deleteCart?uid=72&pid=1
        DeleteUtils.getInstance().post("http://120.27.23.105/product/deleteCart", map, new DeleteCallback() {
            @Override
            public void onSuccess(Object o) {
                Log.e(TAG, "onSuccess: "+o.toString() );
                Log.e(TAG, "onSuccess: "+o.toString() );
                DeleteBean bean =(DeleteBean) o;
                String msg = bean.getMsg();
//                String data = (String) o;
                iv.successDel(msg);
            }

            @Override
            public void onFailed(Exception e) {

            }
        }, DeleteBean.class);
    }
//        解决内存泄漏

    public void detachView() {
        if (iv != null) {
            iv = null;
        }

    }


}
