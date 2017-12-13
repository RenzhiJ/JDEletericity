package com.example.bwie.mydemo.presaenter;


import com.example.bwie.mydemo.bean.ShangJiaBean;
import com.example.bwie.mydemo.callback.DetailView;
import com.example.bwie.mydemo.callback.Service;

import retrofit2.Retrofit;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;



public class DetailPresenter {
    private DetailView inv;
    private Subscription subscribe;
    private String baseUrl="http://120.27.23.105/product/";
    public void attachView(DetailView inv) {
        this.inv = inv;
    }

    public void getNews(String pid) {
        Retrofit retrofit = OkHttpModel.getInstance(baseUrl).getRetrofit();
        Service service = retrofit.create(Service.class);
        subscribe = service.detailData(pid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<ShangJiaBean>() {
                    @Override
                    public void call(ShangJiaBean javaBean) {
                        ShangJiaBean.DataBean data = javaBean.getData();

                        inv.success(data);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {

                    }
                });

    }

    public void detachView() {
        // 当Activity销毁的时候取消订阅时间，防止内存泄漏
        if (subscribe != null) {
            if (subscribe.isUnsubscribed()) {
                subscribe.unsubscribe();
            }
        }
        if (inv != null) {
            inv = null;
        }
    }
}
