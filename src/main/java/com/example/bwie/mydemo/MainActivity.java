package com.example.bwie.mydemo;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.bwie.mydemo.fragment.CartFragment;
import com.example.bwie.mydemo.fragment.ShopFragment;
import com.example.bwie.mydemo.fragment.FaXianFragment;
import com.example.bwie.mydemo.fragment.HomeFragment;
import com.example.bwie.mydemo.fragment.UserFragment;
import com.hjm.bottomtabbar.BottomTabBar;

public class MainActivity extends AppCompatActivity {

    private BottomTabBar mb;
    private int num=5;
    private ImageView img;
    private RelativeLayout start_llt;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            num--;
            if(num==0){
                mb.setVisibility(View.VISIBLE);
                img.setVisibility(View.GONE);
            }
            handler.sendEmptyMessageDelayed(1,1000);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mb=(BottomTabBar)findViewById(R.id.bottom_tab_bar);
        img = (ImageView) findViewById(R.id.img);

        img = (ImageView) findViewById(R.id.img);

        mb=(BottomTabBar)findViewById(R.id.bottom_tab_bar);
        ObjectAnimator animator = ObjectAnimator.ofFloat(img, "alpha", 0f, 1f);
        animator.setDuration(3000);
        animator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                mb.setVisibility(View.VISIBLE);
                img.setVisibility(View.GONE);
                start_llt.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        animator.start();
        start_llt = (RelativeLayout) findViewById(R.id.star_llt);




        mb.init(getSupportFragmentManager())
                .setImgSize(60,60)
                .setFontSize(10)
                .setTabPadding(4,6,10)
                .setChangeColor(Color.RED, Color.DKGRAY)
                .addTabItem("主页",R.mipmap.home, HomeFragment.class)
                .addTabItem("分类",R.mipmap.liebiao, ShopFragment.class)
                .addTabItem("发现",R.mipmap.find, FaXianFragment.class)
                .addTabItem("购物车",R.mipmap.shoppingcart, CartFragment.class)
                .addTabItem("我的",R.mipmap.mine, UserFragment.class)
                .isShowDivider(false)
                .setOnTabChangeListener(new BottomTabBar.OnTabChangeListener() {
                    @Override
                    public void onTabChange(int position, String name) {

                    }
                });
        handler.sendEmptyMessage(1);
    }
}
