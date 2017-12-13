package com.example.bwie.mydemo.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bwie.mydemo.ImgApp;
import com.example.bwie.mydemo.Main3Activity;
import com.example.bwie.mydemo.R;
import com.example.bwie.mydemo.ScanActivity;
import com.example.bwie.mydemo.adapter.HomeBottomAdapter;
import com.example.bwie.mydemo.adapter.MisaoShaAdapter;
import com.example.bwie.mydemo.adapter.ZhuListAdapter;
import com.example.bwie.mydemo.bean.Data;
import com.example.bwie.mydemo.bean.DataList;
import com.example.bwie.mydemo.bean.HomeListBean;
import com.example.bwie.mydemo.callback.HomeListView;
import com.example.bwie.mydemo.callback.INewsView;
import com.example.bwie.mydemo.presaenter.HomeListPresenter;
import com.example.bwie.mydemo.presaenter.NewsPresenter;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.youth.banner.Banner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


public class HomeFragment extends Fragment implements View.OnClickListener, INewsView, HomeListView {
    @BindView(R.id.titlesss)
    TextView titlesss;
    Unbinder unbinder;
    private List<String> list = new ArrayList<String>();
    private static final String TAG = "HomeFragment";
    private List<DataList> datas = new ArrayList<>();
    private List<DataList> bottomList = new ArrayList<>();
    private List<Data> imgDatas = new ArrayList<>();
    private Banner ban;
    String path = "http://120.27.23.105/ad/getAd";
    private View view;
    private ImageView imgSao;
    private RecyclerView gridList;
    private RecyclerView rcy;
    private MisaoShaAdapter adapter;
    private List<HomeListBean.DataBean> homelist = new ArrayList<>();
    private ZhuListAdapter homeListAdapter;
    private HomeListPresenter homelistPresenter;
    //    private NewsPresenter newsPresenter;
    private RecyclerView rcyBottom;
    private TextView miaosha_shi;
    private TextView miaosha_minter;
    private TextView miaosha_second;
    private TextView miaosha_time;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            setTime();
            handler.sendEmptyMessageDelayed(1, 1000);

        }
    };
    private HomeBottomAdapter homeBottomAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.home_fragment, container, false);

        initView();
        imgSao.setOnClickListener(this);
        homeListPresenter();

        handler.sendEmptyMessageDelayed(1, 1000);

        unbinder = ButterKnife.bind(this, view);
        return view;


    }

    //轮播图,秒杀和为你推荐
    private void homeBottomPresenter() {

        NewsPresenter newsPresenters = new NewsPresenter();
        newsPresenters.attachView(this);
        newsPresenters.getNews();
//
        //秒杀的适配器
        adapter = new MisaoShaAdapter(getContext(), datas);
        //秒杀管理器
        LinearLayoutManager manager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);

        rcy.setLayoutManager(manager);

        GridLayoutManager man = new GridLayoutManager(getContext(), 2);

        rcyBottom.setLayoutManager(man);
        homeBottomAdapter = new HomeBottomAdapter(getContext(), bottomList);
        rcyBottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    private void homeListPresenter() {
        homelistPresenter = new HomeListPresenter();
        homelistPresenter.attachView(this);
        homelistPresenter.getNews();
        StaggeredGridLayoutManager man = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.HORIZONTAL);
        gridList.setLayoutManager(man);
        homeListAdapter = new ZhuListAdapter(getContext(), homelist);
    }


    private void initView() {
        rcy = view.findViewById(R.id.rcy);

        miaosha_shi = view.findViewById(R.id.tv_miaosha_shi);
        miaosha_minter = view.findViewById(R.id.tv_miaosha_minter);
        miaosha_second = view.findViewById(R.id.tv_miaosha_second);
        miaosha_time = view.findViewById(R.id.tv_miaosha_time);
        rcyBottom = view.findViewById(R.id.rcy_bottom);
        gridList = view.findViewById(R.id.grid_list);
        ban = view.findViewById(R.id.ban);
        imgSao = view.findViewById(R.id.iv_sao);
        homeBottomPresenter();


    }

    private void setBanner(List<Data> imagedatas) {
        ArrayList<String> list = new ArrayList<>();
        for (Data array : imagedatas) {

            list.add(array.getIcon());
        }

        ban.setImageLoader(new ImgApp());//引用ImgApp,加载里面的东西
        ban.setImages(list);
        ban.isAutoPlay(true);
        ban.setDelayTime(2000);
        ban.start();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_sao:


                IntentIntegrator integrator = new IntentIntegrator((Activity) getContext());
                // 设置要扫描的条码类型，ONE_D_CODE_TYPES：一维码，QR_CODE_TYPES-二维码
                integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
                integrator.setCaptureActivity(ScanActivity.class);
                integrator.setPrompt("请扫描二维码"); //底部的提示文字，设为""可以置空
                integrator.setCameraId(0); //前置或者后置摄像头
                integrator.setBeepEnabled(false); //扫描成功的「哔哔」声，默认开启
                integrator.setBarcodeImageEnabled(true);//是否保留扫码成功时候的截图
                integrator.initiateScan();

                break;
        }


    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        IntentResult scanResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (scanResult != null) {
            String result = scanResult.getContents();
            Log.e("HYN", result);
            Toast.makeText(getContext(), result, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void success(List<DataList> list, List<DataList> data, List<Data> imagedatas) {
        datas.addAll(list);
        bottomList.addAll(data);
        setBanner(imagedatas);
        rcy.setAdapter(adapter);
        rcyBottom.setAdapter(homeBottomAdapter);


    }

    @Override
    public void failed(String tag, Exception e) {

    }

    @Override
    public void suc(List<HomeListBean.DataBean> data) {
        homelist.addAll(data);
        gridList.setAdapter(homeListAdapter);

    }

    @Override
    public void fai(String tag, Exception e) {

    }

    public void setTime() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date curDate = new Date(System.currentTimeMillis());
        String format = df.format(curDate);
        StringBuffer buffer = new StringBuffer();
        String substring = format.substring(0, 11);
        buffer.append(substring);
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);

        if (hour % 2 == 0) {
            miaosha_time.setText(hour + "点场");
            buffer.append((hour + 2));
            buffer.append(":00:00");
        } else {
            miaosha_time.setText((hour - 1) + "点场");
            buffer.append((hour + 1));
            buffer.append(":00:00");
        }
        String totime = buffer.toString();
        try {
            Date date = df.parse(totime);
            Date date1 = df.parse(format);
            long defferenttime = date.getTime() - date1.getTime();
            long days = defferenttime / (1000 * 60 * 60 * 24);
            long hours = (defferenttime - days * (1000 * 60 * 60 * 24)) / (1000 * 60 * 60);
            long minute = (defferenttime - days * (1000 * 60 * 60 * 24) - hours * (1000 * 60 * 60)) / (1000 * 60);
            long seconds = defferenttime % 60000;
            long second = Math.round((float) seconds / 1000);
            miaosha_shi.setText("0" + hours + "");
            if (minute >= 10) {
                miaosha_minter.setText(minute + "");
            } else {
                miaosha_minter.setText("0" + minute + "");
            }
            if (second >= 10) {
                miaosha_second.setText(second + "");
            } else {
                miaosha_second.setText("0" + second + "");
            }


        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.titlesss)
    public void onViewClicked() {
        Intent intent = new Intent(getContext(), Main3Activity.class);
        startActivity(intent);
    }
}
