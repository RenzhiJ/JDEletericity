package com.example.bwie.mydemo.presaenter;

import com.example.bwie.mydemo.bean.MessageBean;
import com.example.bwie.mydemo.callback.CartsCalllBack;
import com.example.bwie.mydemo.callback.ShopCartsView;
import com.example.bwie.mydemo.http.CartHttpUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Carts {
        private ShopCartsView iv;
        public void attach(ShopCartsView iv) {
            this.iv=iv;
        }
        public void getData(String uid){

            Map<String,String> map = new HashMap<>();
            map.put("uid",uid);

            CartHttpUtils.getInstance().post("http://120.27.23.105/product/getCarts", map, new CartsCalllBack() {
                @Override
                public void onSuccess(Object o) {
                    if(o!=null){
                        MessageBean bean = (MessageBean) o;
                        List<MessageBean.DataBean> data = bean.getData();


                        iv.success(data);
                    }

                }

                @Override
                public void onFailed(Exception e) {
                    iv.failed(e);
                }
            }, MessageBean.class);
        }
    public void detach() {
        if(iv!=null){
            iv=null;
        }
    }
}
