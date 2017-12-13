package com.example.bwie.mydemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bwie.mydemo.adapter.PriceAdapter;
import com.example.bwie.mydemo.bean.YueBingBean;
import com.example.bwie.mydemo.callback.Service;
import com.example.bwie.mydemo.callback.YueBingView;
import com.example.bwie.mydemo.presaenter.YblistPresenter;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Main2Activity extends AppCompatActivity implements XRecyclerView.LoadingListener, YueBingView {

    @BindView(R.id.iv_kind_back)
    ImageView mIvKindBack;
    @BindView(R.id.iv_kind_type)
    ImageView mIvKindType;
    @BindView(R.id.tv_shaixuan)
    TextView mTvShaixuan;
    @BindView(R.id.btn_jingdong)
    Button mBtnJingdong;
    @BindView(R.id.btn_new_shop)
    Button mBtnNewShop;
    @BindView(R.id.btn_grade)
    Button mBtnGrade;
    @BindView(R.id.btn_caizhi)
    Button mBtnCaizhi;
    @BindView(R.id.rcv_kind)
    XRecyclerView mRcvKind;
    @BindView(R.id.ll_sao)
    LinearLayout llSao;
    @BindView(R.id.tes)
    TextView tes;
    @BindView(R.id.ll_search)
    LinearLayout llSearch;
    @BindView(R.id.ll_msg)
    LinearLayout llMsg;
    @BindView(R.id.ll_head)
    LinearLayout llHead;
    @BindView(R.id.ll_shaixuan)
    LinearLayout llShaixuan;
    @BindView(R.id.ll_nest_toolBar)
    LinearLayout llNestToolBar;
    @BindView(R.id.ll_kind_scro)
    LinearLayout llKindScro;
    private List<YueBingBean.DataBean> list = new ArrayList<>();
    private String url = "http://120.27.23.105/product/";
    private int page = 1;
    private int sort = 0;
    private Service service;
    private PriceAdapter adapter;
    private String pscid;
    private boolean isFresh = true;
    private static final String TAG = "Main2Activity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        pscid = intent.getStringExtra("pscid");
        Log.e("zxz","asdasd0"+pscid);
        loadData();

        adapter = new PriceAdapter(this, list);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        mRcvKind.setLayoutManager(manager);
        mRcvKind.setLoadingMoreEnabled(true);
        mRcvKind.setLoadingListener(this);
        mRcvKind.setAdapter(adapter);
        loadData();

    }

    private void loadData() {

        YblistPresenter presenter = new YblistPresenter(this);
        presenter.getData(url, pscid, page, sort);

    }

    @OnClick({R.id.iv_kind_back, R.id.iv_kind_type, R.id.tv_shaixuan, R.id.btn_jingdong, R.id.btn_new_shop, R.id.btn_grade, R.id.btn_caizhi, R.id.rcv_kind})
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.iv_kind_back:
                finish();
                break;

        }
    }

    @Override
    public void onRefresh() {
        isFresh = true;
        page = 1;
        loadData();
    }

    @Override
    public void onLoadMore() {
        isFresh = false;
        page += 1;

        loadData();
    }

    @Override
    public void onSuccess(List<YueBingBean.DataBean> newslist) {
        if (isFresh) {
            mRcvKind.refreshComplete();

        } else {
            mRcvKind.loadMoreComplete();

        }
        if (newslist != null) {
            if (isFresh) {
                list.clear();
            }
            list.addAll(newslist);
            adapter.notifyDataSetChanged();
        }
        if (page == 3) {
            Toast.makeText(this, "没有更多数据啦!", Toast.LENGTH_LONG).show();
            mRcvKind.loadMoreComplete();

        }

    }

    @Override
    public void onFailed(Exception e) {

    }

    @OnClick(R.id.tes)
    public void onViewClicked() {

    }
}
