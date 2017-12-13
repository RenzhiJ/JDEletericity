package com.example.bwie.mydemo.presaenter;


import com.example.bwie.mydemo.bean.YueBingBean;
import com.example.bwie.mydemo.callback.YueBinglistCallback;
import com.example.bwie.mydemo.callback.YueBingView;

import java.util.List;

public class YblistPresenter {
    private YueBingView iv;

    public YblistPresenter(YueBingView iv) {
        this.iv = iv;
    }
    public void getData( String url,String pscid, int page, int sort){
        YBlistModel.getInstance().get(url,pscid, page,sort, new YueBinglistCallback() {
            @Override
            public void success(YueBingBean body) {
                List<YueBingBean.DataBean> newslist = body.getData();
                iv.onSuccess(newslist);
            }

            @Override
            public void failed(Exception e) {

            }
        });
    }
}
