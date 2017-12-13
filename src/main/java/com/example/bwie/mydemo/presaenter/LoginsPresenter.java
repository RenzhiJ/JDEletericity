package com.example.bwie.mydemo.presaenter;

import com.example.bwie.mydemo.bean.LoginBean;
import com.example.bwie.mydemo.callback.CartsCalllBack;
import com.example.bwie.mydemo.callback.LoginView;
import com.example.bwie.mydemo.http.CartHttpUtils;

import java.util.HashMap;
import java.util.Map;


public class LoginsPresenter {
        private LoginView iv;
        public void attach(LoginView iv) {
            this.iv=iv;
        }
        public void getData(String uid){

            Map<String,String> map = new HashMap<>();
            map.put("uid",uid+"");

            CartHttpUtils.getInstance().post("http://120.27.23.105/user/getUserInfo", map, new CartsCalllBack() {
                @Override
                public void onSuccess(Object o) {
                    LoginBean bean = (LoginBean) o;
                    LoginBean.DataBean data = bean.getData();


                    iv.success(data);
                }

                @Override
                public void onFailed(Exception e) {
                    iv.failed(e);
                }
            }, LoginBean.class);
        }
}
