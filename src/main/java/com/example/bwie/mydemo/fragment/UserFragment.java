package com.example.bwie.mydemo.fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.bwie.mydemo.LoginActivity;
import com.example.bwie.mydemo.R;
import com.example.bwie.mydemo.UserActivity;
import com.example.bwie.mydemo.bean.LoginBean;
import com.example.bwie.mydemo.callback.LoginView;
import com.example.bwie.mydemo.presaenter.LoginsPresenter;

import static android.content.Context.MODE_PRIVATE;
import static com.bumptech.glide.gifdecoder.GifHeaderParser.TAG;


public class UserFragment extends Fragment implements LoginView {

    private RelativeLayout rlt;
    private View view;
    private ImageView qqIcon;
    private String headIcon;
    private TextView loginName;
    private ImageView anotherHead;
    private ImageView firstHead;
    private int num ;
    private LoginBean.DataBean datas;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.user_fragment, container, false);
        rlt = view.findViewById(R.id.rlt);
        initView();

            rlt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    switch (num){
                        case 0:
                            Intent intent = new Intent(getContext(), LoginActivity.class);
                            startActivityForResult(intent,100);
                            break;
                        case 1:
                            Intent intent2   = new Intent(getContext(), UserActivity.class);
                            startActivity(intent2);
                            break;

                    }


                }
            });

        return view;
        
    }

    private void initView() {
        qqIcon = view.findViewById(R.id.f5_headpig);
//        f5_deng_tex
        anotherHead = view.findViewById(R.id.another_head);
        firstHead = view.findViewById(R.id.f5_head);
        loginName = view.findViewById(R.id.f5_deng_tex);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

    }

    @Override
    public void onResume() {
        super.onResume();
        SharedPreferences preferences = getContext().getSharedPreferences("data", MODE_PRIVATE);
        boolean isLogin = preferences.getBoolean("isLogin", false);
        String uid = preferences.getString("id", "s");
        if(isLogin){
            LoginsPresenter presenter = new LoginsPresenter();
            presenter.attach(this);
            presenter.getData(uid);

        }else{
            num=0;
            anotherHead.setVisibility(View.GONE);
            firstHead.setVisibility(View.VISIBLE);
            loginName.setText("登录/注册 >");
        }

        Log.e(TAG, "onResume: "+isLogin+uid );
    }

    @Override
    public void success(LoginBean.DataBean data) {
        datas=data;
        num=1;
        loginName.setText(datas.getUsername());
        anotherHead.setVisibility(View.VISIBLE);
        firstHead.setVisibility(View.GONE);
    }

    @Override
    public void failed(Exception e) {

    }
}
