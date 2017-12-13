package com.example.bwie.mydemo.presaenter;


import android.util.Log;

import com.example.bwie.mydemo.bean.Tianjia;
import com.example.bwie.mydemo.bean.LoginBean;
import com.example.bwie.mydemo.callback.AddView;
import com.example.bwie.mydemo.callback.LoginCalllBack;
import com.example.bwie.mydemo.http.LoginHttpUtils;

import java.util.HashMap;
import java.util.Map;

import static android.content.ContentValues.TAG;


public class AddPresenter {
    private AddView iv;
    public void attach(AddView iv){
        this.iv=iv;
    }
    public void getData(String pid,String uid){
//        https://www.zhaoapi.cn/product/addCart?uid=71&pid=71
        //给map集合添加数据
        Map<String,String> map = new HashMap<>();
        map.put("pid",pid);
        map.put("uid",uid);
        //调用
        LoginHttpUtils.getInstance().post("http://120.27.23.105/product/addCart", map, new LoginCalllBack() {
            @Override
            public void onSuccess(Object o) {
                Log.e(TAG, "onSuccess: "+o.toString() );
                Log.e(TAG, "onSuccess: "+o.toString() );
                LoginBean bean =(LoginBean) o;
                String msg = bean.getMsg();
//                String data = (String) o;
                iv.successAdd(msg);
            }

            @Override
            public void onFailed(Exception e) {

            }
        }, Tianjia.class);
    }
    //解决内存泄漏
    public void detachView() {
        if (iv != null) {
            iv = null;
        }

    }
}
