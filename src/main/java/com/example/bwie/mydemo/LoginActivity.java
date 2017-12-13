package com.example.bwie.mydemo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.bwie.mydemo.bean.LoginBean;
import com.example.bwie.mydemo.bean.qqBean;
import com.google.gson.Gson;
import com.tencent.connect.UserInfo;
import com.tencent.connect.auth.QQToken;
import com.tencent.connect.common.Constants;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "MainActivity";
    private static final String APP_ID = "1105602574";//官方获取的APPID
    private Tencent mTencent;
    private BaseUiListener mIUiListener;
    private UserInfo mUserInfo;
    //    private TextView tvSex;
//    private TextView tvName;
    private ImageView qqIcon;
    /**
     * 用户名/邮箱/手机号
     */
    private EditText mEditnameLogin;
    /**
     * 请输入密码
     */
    private EditText mEditpwdLogin;
    /**
     * 登录
     */
    private Button mBtnLogin;
    /**
     * 手机快速注册
     */
    private Button mBtnRegist;
    /**
     * 忘记密码
     */
    private Button mBtnForgetpwd;
    private ImageView mWeixinIcon;
//    private TextView tvCity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //传入参数APPID和全局Context上下文
        mTencent = Tencent.createInstance(APP_ID, LoginActivity.this.getApplicationContext());

        initView();

    }

    private void initView() {
        qqIcon = (ImageView) findViewById(R.id.qq_icon);


        mEditnameLogin = (EditText) findViewById(R.id.editname_login);
        mEditpwdLogin = (EditText) findViewById(R.id.editpwd_login);
        mBtnLogin = (Button) findViewById(R.id.btn_login);
        mBtnLogin.setOnClickListener(this);
        mBtnRegist = (Button) findViewById(R.id.btn_regist);
        mBtnRegist.setOnClickListener(this);
        mBtnForgetpwd = (Button) findViewById(R.id.btn_forgetpwd);
        mBtnForgetpwd.setOnClickListener(this);
        mWeixinIcon = (ImageView) findViewById(R.id.weixin_icon);


    }



    @Override
    public void onClick(View v) {
        String mobile = mEditnameLogin.getText().toString().trim();
        String password = mEditpwdLogin.getText().toString().trim();
        switch (v.getId()) {
            default:
                break;
            case R.id.qq_icon:
                buttonLogin(v);

                break;
            case R.id.btn_login:
                boolean flag = checkData(mobile, password);
                Log.i("zxz",flag+"");
                if(flag){
                    register(mobile, password);
                }
                break;
            case R.id.btn_regist:
                Intent intent = new Intent(LoginActivity.this, RegistActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_forgetpwd:

                break;
        }
    }

    public void buttonLogin(View v) {
        /**通过这句代码，SDK实现了QQ的登录，这个方法有三个参数，第一个参数是context上下文，第二个参数SCOPO 是一个String类型的字符串，表示一些权限
         官方文档中的说明：应用需要获得哪些API的权限，由“，”分隔。例如：SCOPE = “get_user_info,add_t”；所有权限用“all”
         第三个参数，是一个事件监听器，IUiListener接口的实例，这里用的是该接口的实现类 */
        mIUiListener = new BaseUiListener();
        //all表示获取所有权限
        mTencent.login(LoginActivity.this, "all", mIUiListener);
    }
    /**
     * 自定义监听器实现IUiListener接口后，需要实现的3个方法
     * onComplete完成 onError错误 onCancel取消
     */
    private class BaseUiListener implements IUiListener {

        @Override
        public void onComplete(Object response) {
            Toast.makeText(LoginActivity.this, "授权成功", Toast.LENGTH_SHORT).show();
            Log.e(TAG, "response:" + response);
            JSONObject obj = (JSONObject) response;
            try {
                String openID = obj.getString("openid");
                String accessToken = obj.getString("access_token");
                String expires = obj.getString("expires_in");
                mTencent.setOpenId(openID);
                mTencent.setAccessToken(accessToken, expires);
                QQToken qqToken = mTencent.getQQToken();
                mUserInfo = new UserInfo(getApplicationContext(), qqToken);
                mUserInfo.getUserInfo(new IUiListener() {
                    @Override
                    public void onComplete(Object response) {
                        Log.e(TAG, "登录成功" + response.toString());
                        Gson gson = new Gson();
                        String data = gson.toJson(response);
                        qqBean qqBean = gson.fromJson(data, qqBean.class);
                        Intent intent = new Intent();

                        intent.putExtra("headIcon", qqBean.getNameValuePairs().getFigureurlQQ2());
                        intent.putExtra("name", qqBean.getNameValuePairs().getNickname());
                        setResult(200, intent);
                        finish();

                    }

                    @Override
                    public void onError(UiError uiError) {
                        Log.e(TAG, "登录失败" + uiError.toString());

                    }

                    @Override
                    public void onCancel() {
                        Log.e(TAG, "登录取消");

                    }
                });
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onError(UiError uiError) {
            Toast.makeText(LoginActivity.this, "授权失败", Toast.LENGTH_SHORT).show();

        }

        @Override
        public void onCancel() {
            Toast.makeText(LoginActivity.this, "授权取消", Toast.LENGTH_SHORT).show();

        }

    }

    /**
     * 在调用Login的Activity或者Fragment中重写onActivityResult方法
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == Constants.REQUEST_LOGIN) {
            Tencent.onActivityResultData(requestCode, resultCode, data, mIUiListener);
        }
        super.onActivityResult(requestCode, resultCode, data);
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
//            if()
        return  true;
    }
    private void register(String mobile, String password) {
        RequestParams params = new RequestParams("http://120.27.23.105/user/login");
//                params.setSslSocketFactory(...); // 设置ssl
        params.addQueryStringParameter("mobile", mobile);
        params.addQueryStringParameter("password", password);

        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.i("zxz","onsuccess"+result);
                Gson gson = new Gson();
                LoginBean loginBean = gson.fromJson(result, LoginBean.class);
                if (loginBean.getCode().equals("0")) {
//                    Intent intent = new Intent(LoginActivity.this, us.class);
//                    startActivity(intent);
                    SharedPreferences preferences = getSharedPreferences("data",MODE_PRIVATE);
                    SharedPreferences.Editor edit = preferences.edit();
                    edit.putBoolean("isLogin",true);
                    loginBean.getData().getUid();
                    edit.putString("id",loginBean.getData().getUid()+"");
                    edit.commit();
                    finish();
                }else{
                    Toast.makeText(LoginActivity.this,loginBean.getMsg(),Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Toast.makeText(x.app(), ex.getMessage(), Toast.LENGTH_SHORT).show();
                Log.i("zxz","onsuccess"+ex.getMessage());
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
