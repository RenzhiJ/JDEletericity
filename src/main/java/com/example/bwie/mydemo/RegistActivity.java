package com.example.bwie.mydemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.bwie.mydemo.bean.RegistBean;
import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class RegistActivity extends AppCompatActivity {

    @BindView(R.id.regist_back)
    ImageView mRegistBack;
    @BindView(R.id.regist_username)
    EditText mRegistUsername;
    @BindView(R.id.regist_password)
    EditText mRegistPassword;
    @BindView(R.id.btn_regist)
    Button mBtnRegist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist);
        ButterKnife.bind(this);

    }

    @OnClick({R.id.regist_back, R.id.btn_regist})
    public void onClick(View v) {
        String mobile = mRegistUsername.getText().toString().trim();
        String password = mRegistPassword.getText().toString().trim();

        switch (v.getId()) {
            default:
                break;
            case R.id.regist_back:
                finish();
                break;

            case R.id.btn_regist:
                boolean flag = checkData(mobile, password);
                if(flag){
                    register(mobile, password);
                }
                break;
        }
    }

    private boolean checkData(String mobile, String password) {
        if(TextUtils.isEmpty(mobile)||TextUtils.isEmpty(password)){
            Toast.makeText(this,"不能为空",Toast.LENGTH_SHORT).show();
            return false;
        }
        if(password.length()<6){
            Toast.makeText(this,"密码长度不能小于6",Toast.LENGTH_SHORT).show();
            return false;
        }
        return  true;
    }
    private void register(String mobile, String password) {
        RequestParams params = new RequestParams("http://120.27.23.105/user/reg");
        params.addQueryStringParameter("mobile", mobile);
        params.addQueryStringParameter("password", password);

        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Gson gson = new Gson();
                RegistBean registBean = gson.fromJson(result, RegistBean.class);
                Toast.makeText(RegistActivity.this,registBean.getMsg(),Toast.LENGTH_SHORT).show();
                if (registBean.getCode().equals("0")) {

                    finish();

                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Toast.makeText(x.app(), ex.getMessage(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(CancelledException cex) {
                Toast.makeText(x.app(), "cancelled", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFinished() {

            }
        });
    }
}
