package com.example.bwie.mydemo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.bwie.mydemo.bean.ShangJiaBean;
import com.example.bwie.mydemo.callback.AddView;
import com.example.bwie.mydemo.callback.DetailView;
import com.example.bwie.mydemo.presaenter.AddPresenter;
import com.example.bwie.mydemo.presaenter.DetailPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DetailActivity extends AppCompatActivity implements DetailView, AddView {


    @BindView(R.id.add_carts)
    Button mAddCarts;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.tv_old_price)
    TextView mTvOldPrice;
    @BindView(R.id.tv_new_price)
    TextView mTvNewPrice;
    @BindView(R.id.detail_icon)
    ImageView detailIcon;
    @BindView(R.id.detail_back)
    ImageView detailBack;
    private String uid;
    private String pid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
        mTvOldPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);

        Intent intent = getIntent();
        pid = intent.getStringExtra("pid");
        Log.e("zxz", "onCreate: "+ pid);
        SharedPreferences preferences = getSharedPreferences("data", MODE_PRIVATE);
        uid = preferences.getString("id", "s");
        Log.e("zxz", "onCreate: "+uid );
        DetailPresenter presenter = new DetailPresenter();
        presenter.attachView(this);
        presenter.getNews(pid);
    }

    @OnClick({ R.id.add_carts, R.id.tv_title, R.id.tv_old_price, R.id.tv_new_price, R.id.detail_back})
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.detail_back:
                finish();
                break;
            case R.id.add_carts:
                SharedPreferences preferences = getSharedPreferences("data",MODE_PRIVATE);
                SharedPreferences.Editor edit = preferences.edit();
                edit.putBoolean("isEmpty",false);
                edit.commit();
                AddPresenter addPresenter = new AddPresenter();
                addPresenter.attach(this);
                addPresenter.getData(pid,uid);

                break;
            case R.id.tv_title:
                break;
            case R.id.tv_old_price:
                break;
            case R.id.tv_new_price:
                break;
        }
    }

    @Override
    public void failed(Exception e) {

    }

    @Override
    public void success(ShangJiaBean.DataBean data) {
        if(data!=null){
            String images = data.getImages();
            String[] split = images.split("[|]");
            String title = data.getTitle();
            double price = data.getPrice();
            double bargainPrice = data.getBargainPrice();
            mTvOldPrice.setText("原价:" + bargainPrice);
            mTvNewPrice.setText("优惠价:" + price);
            mTvTitle.setText(title);
            Uri uri = Uri.parse(split[0]);
            Glide.with(this).load(uri).into(detailIcon);
        }


    }

    @Override
    public void successAdd(String data) {
        Toast.makeText(this,data,Toast.LENGTH_SHORT).show();

    }

    @Override
    public void failedAdd(Exception e) {

    }



}