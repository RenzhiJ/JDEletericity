package com.example.bwie.mydemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.bwie.mydemo.bean.SeachBean;
import com.example.bwie.mydemo.callback.Seach;
import com.example.bwie.mydemo.presaenter.SeachPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Main3Activity extends AppCompatActivity implements Seach {

    @BindView(R.id.detail_back)
    ImageView detailBack;
    @BindView(R.id.editText)
    EditText editText;
    @BindView(R.id.button)
    Button button;
    private SeachPresenter presenter;
    private int pscid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        ButterKnife.bind(this);
        presenter = new SeachPresenter();
        presenter.setSeach(this);

    }

    @OnClick({R.id.detail_back, R.id.editText, R.id.button})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.detail_back:
               finish();

                break;
            case R.id.editText:

                break;
            case R.id.button:
                String s = editText.getText().toString().trim();
                Log.e("zxz",s);
                presenter.getData(s);

                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onSuccess(Object o) {
        SeachBean bean = (SeachBean)o;
        if(bean!=null){
            List<SeachBean.DataBean> data = bean.getData();
            if(data!=null){
                for (SeachBean.DataBean dataBean : data) {

                    pscid = dataBean.getPscid();
                    Log.e("zxz",pscid+dataBean.getTitle());

                }
            }
        }
        Intent intent = new Intent(Main3Activity.this, Main2Activity.class);
        intent.putExtra("pscid",pscid+"");
        startActivity(intent);

    }

    @Override
    public void onFailed(Exception e) {

    }
}
