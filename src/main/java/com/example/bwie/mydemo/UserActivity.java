package com.example.bwie.mydemo;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UserActivity extends AppCompatActivity {

    @BindView(R.id.log_off)
    Button mLogOff;
    @BindView(R.id.user_back)
    ImageView userBack;

    @BindView(R.id.user_img)
    ImageView mUserImg;
    @BindView(R.id.user_name)
    TextView mUserName;
    @BindView(R.id.user_llt)
    LinearLayout userLlt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        ButterKnife.bind(this);

    }

    @OnClick({R.id.log_off, R.id.user_back, R.id.user_img, R.id.user_name,R.id.user_llt})
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.log_off:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("确定退出登录吗?");
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        SharedPreferences preferences = getSharedPreferences("data", MODE_PRIVATE);
                        SharedPreferences.Editor edit = preferences.edit();
                        edit.putBoolean("isLogin", false);
                        edit.commit();
                        finish();
                    }
                });
                builder.setNegativeButton("取消", null);

                builder.create();
                builder.show();

                break;
            case R.id.user_back:
                finish();
                break;
            case R.id.user_img:
                break;
            case R.id.user_llt:
                Intent intent = new Intent(UserActivity.this,PersonActivity.class);

                startActivity(intent);
                break;
        }
    }
}
